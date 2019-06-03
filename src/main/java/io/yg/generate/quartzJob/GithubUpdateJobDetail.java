package io.yg.generate.quartzJob;

import io.yg.generate.GenerateResource;
import io.yg.util.NetWorkUtil;
import io.yg.util.ShellUtil;
import io.yg.util.githubapi.ArticleUrl;
import io.yg.util.githubapi.GithubRepContent;
import org.apache.commons.io.FileUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Creat by GuoJF on
 */
public class GithubUpdateJobDetail implements Job {


    private static List<GithubRepContent> githubRepContents;

    static {

        githubRepContents = new ArrayList<>();
    }

    /*
     * 更新Job
     * */


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        if (NetWorkUtil.isRepUpdate("https://api.github.com/repos/GuoJiafeng/ProblemRepository")) {


          //  ShellUtil.exceScript("/usr/local/git/bin/git  -C /home/blog/ProblemRepository  pull ", ShellUtil.LINUX);

            if (githubRepContents.size() != 0) {
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

        } else {

            System.out.println("当前不需要更新！");
        }
    }
}
