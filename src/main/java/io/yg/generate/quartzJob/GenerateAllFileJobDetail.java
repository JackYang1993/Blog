package io.yg.generate.quartzJob;

import io.yg.generate.GenerateResource;
import io.yg.util.NetWorkUtil;
import io.yg.util.ShellUtil;
import io.yg.util.githubapi.ArticleUrl;
import io.yg.util.githubapi.GithubRepContent;
import org.apache.commons.io.FileUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Create by GuoJF on mac
 */

public class GenerateAllFileJobDetail implements Job {

    private static List<GithubRepContent> githubRepContents;

    static {

        githubRepContents = new ArrayList<>();
    }


    /*
     *
     * 启动时初始化
     * */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        File file = new File("/home/blog/ProblemRepository");

        if (!file.exists()) {
            file.mkdir();

            // String s = ShellUtil.exceScript("/usr/local/git/bin/git -C /home/blog/  clone https://github.com/GuoJiafeng/ProblemRepository.git ", ShellUtil.LINUX);
            //System.out.println(s);

            try {
                githubRepContents = NetWorkUtil.getAllFile(ArticleUrl.ARTICLEURL_ROOT, ArticleUrl.ARTICLEURL_IMAGE);

                for (GithubRepContent githubRepContent : githubRepContents) {

                    String filename = "";

                    if (githubRepContent.getName().contains(".md")) {
                        filename = "/home/blog/ProblemRepository/" + githubRepContent.getName();
                    } else {
                        filename = "/home/blog/ProblemRepository/assets/" + githubRepContent.getName();

                    }


                    FileUtils.copyURLToFile(new URL(githubRepContent.getDownload_url()), new File(filename));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            GenerateResource.copyCSSResgource();

        } else {

            if (githubRepContents.size() == 0) {
                try {
                    List<GithubRepContent> allFile = NetWorkUtil.getAllFile(ArticleUrl.ARTICLEURL_ROOT, ArticleUrl.ARTICLEURL_IMAGE);

                    for (int i = 0; i < allFile.size(); i++) {


                        GithubRepContent githubRepContent = allFile.get(i);

                        GithubRepContent githubRepContent1 = githubRepContents.get(i);

                        if (!githubRepContent1.getSha().equals(githubRepContent.getSha())) {


                            String filename = "";

                            if (githubRepContent.getName().contains(".md")) {
                                filename = "/home/blog/ProblemRepository/" + githubRepContent.getName();
                            } else {
                                filename = "/home/blog/ProblemRepository/assets/" + githubRepContent.getName();

                            }


                            FileUtils.copyURLToFile(new URL(githubRepContent1.getDownload_url()), new File(filename));


                        }

                    }

                    githubRepContents.clear();

                    githubRepContents.addAll(allFile);


                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else {

                try {
                    githubRepContents.addAll(NetWorkUtil.getAllFile(ArticleUrl.ARTICLEURL_ROOT, ArticleUrl.ARTICLEURL_IMAGE));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //String s = ShellUtil.exceScript("/usr/local/git/bin/git -C /home/blog/ProblemRepository   pull ", ShellUtil.LINUX);


            try {
                GenerateResource.generateIndex();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(s);


        }


        System.out.println("GenerateAllFileJobDetail  zhixing");


    }
}
