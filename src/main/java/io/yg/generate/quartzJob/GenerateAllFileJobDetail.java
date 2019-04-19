package io.yg.generate.quartzJob;

import io.yg.generate.FileScanner;
import io.yg.util.ShellUtil;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.io.File;


/**
 * Creat by GuoJF on mac
 */

public class GenerateAllFileJobDetail implements Job {


    /*
     *
     * 启动时初始化
     * */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        File file = new File("/home/blog/");

        if (!file.exists()) {
            file.mkdir();
            ShellUtil.exceScript("/usr/local/git/bin/git -C /home/blog/  clone https://github.com/GuoJiafeng/ProblemRepository.git ", ShellUtil.LINUX);

        } else {

            ShellUtil.exceScript("/usr/local/git/bin/git -C /home/blog/ProblemRepository   pull ", ShellUtil.LINUX);
        }


    }
}
