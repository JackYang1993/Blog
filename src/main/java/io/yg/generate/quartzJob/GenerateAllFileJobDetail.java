package io.yg.generate.quartzJob;

import io.yg.generate.GenerateResource;
import io.yg.util.ShellUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import java.io.File;


/**
 * Create by GuoJF on mac
 */

public class GenerateAllFileJobDetail implements Job {

    /*
     *
     * 启动时初始化
     * */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        File file = new File("/home/blog/ProblemRepository");

        if (!file.exists()) {
            file.mkdir();
            String s = ShellUtil.exceScript("/usr/local/git/bin/git -C /home/blog/  clone https://github.com/GuoJiafeng/ProblemRepository.git ", ShellUtil.LINUX);
            System.out.println(s);
            GenerateResource.copyCSSResgource();

        } else {

            String s = ShellUtil.exceScript("/usr/local/git/bin/git -C /home/blog/ProblemRepository   pull ", ShellUtil.LINUX);

            try {
                GenerateResource.generateIndex();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(s);


        }


        System.out.println("GenerateAllFileJobDetail  zhixing");


    }
}
