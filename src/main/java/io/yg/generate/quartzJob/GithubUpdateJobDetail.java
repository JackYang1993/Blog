package io.yg.generate.quartzJob;

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



        ShellUtil.exceScript("/usr/local/git/bin/git  -C /home/blog/ProblemRepository  pull ", ShellUtil.LINUX);

    }
}
