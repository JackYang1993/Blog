package io.yg.generate.quartzJob;

import io.yg.generate.FileScanner;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Create by GuoJF on 2019/4/19
 */
public class WatchFillChangeJobDetail implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {




        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FileScanner fileScanner = new FileScanner();

        try {
            fileScanner.start("/home/blog/");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
