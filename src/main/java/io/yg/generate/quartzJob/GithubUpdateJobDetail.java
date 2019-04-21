package io.yg.generate.quartzJob;

import io.yg.generate.GenerateResource;
import io.yg.util.NetWorkUtil;
import io.yg.util.ShellUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Creat by GuoJF on
 */
public class GithubUpdateJobDetail implements Job {

    /*
     * 更新Job
     * */


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        if (NetWorkUtil.isRepUpdate("https://api.github.com/repos/GuoJiafeng/ProblemRepository")) {


            ShellUtil.exceScript("/usr/local/git/bin/git  -C /home/blog/ProblemRepository  pull ", ShellUtil.LINUX);


        } else {

            System.out.println("当前不需要更新！");
        }
    }
}
