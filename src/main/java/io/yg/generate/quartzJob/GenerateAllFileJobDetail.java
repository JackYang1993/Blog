package io.yg.generate.quartzJob;

import io.yg.generate.FileScanner;
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

   /* private static List<GithubRepContent> githubRepContents;

    static {

        githubRepContents = new ArrayList<>();
    }*/


    /*
     *
     * 启动时初始化
     * */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {



        FileScanner fileScanner = new FileScanner();

        try {

            System.out.println("开始监听！");
            fileScanner.start("D:\\home\\blog\\ProblemRepository");
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File("/home/blog/ProblemRepository");

        if (!file.exists()) {
            file.mkdirs();

            //String s = ShellUtil.exceScript("/usr/local/git/bin/git -C /home/blog/  clone https://github.com/GuoJiafeng/ProblemRepository.git ", ShellUtil.LINUX);
            //System.out.println(s);

          /*  try {
                githubRepContents = NetWorkUtil.getAllFile(ArticleUrl.ARTICLEURL_ROOT, ArticleUrl.ARTICLEURL_IMAGE);

                for (GithubRepContent githubRepContent : githubRepContents) {

                    String filename = "";

                    if (githubRepContent.getName().contains(".md")) {
                        filename = "/home/blog/ProblemRepository/" + githubRepContent.getName();
                    } else {
                        filename = "/home/blog/ProblemRepository/assets/" + githubRepContent.getName();

                    }


                    // FileUtils.copyURLToFile(new URL(githubRepContent.getDownload_url()), new File(filename));


                    //wget  --no-check-certificate

                  //  ShellUtil.exceScript("wget  --no-check-certificate '" + githubRepContent.getDownload_url() + "'  -O " + filename, ShellUtil.LINUX);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/


            GenerateResource.copyCSSResgource();

        } else {

           /* if (githubRepContents.size() == 0) {
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


                            //FileUtils.copyURLToFile(new URL(githubRepContent1.getDownload_url()), new File(filename));

                            ShellUtil.exceScript("wget  --no-check-certificate '" + githubRepContent.getDownload_url() + "'  -O " + filename, ShellUtil.LINUX);
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
*/
           // String s = ShellUtil.exceScript("/usr/local/git/bin/git -C /home/blog/ProblemRepository   pull ", ShellUtil.LINUX);
            GenerateResource.copyCSSResgource();


            GenerateResource.fileScanner(new File("d:/home/blog/ProblemRepository"));


            GenerateResource.generateIndex();





           // GenerateResource.generateIndex();

           // System.out.println(s);

            System.out.println("GenerateAllFileJobDetail  zhixing");

        }




    }
}
