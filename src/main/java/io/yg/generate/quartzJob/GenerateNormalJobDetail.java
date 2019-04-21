package io.yg.generate.quartzJob;

import io.yg.generate.GenerateResource;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;

/**
 * Creat by GuoJF on
 */
public class GenerateNormalJobDetail implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        GenerateResource.fileScanner(new File("/home/blog/ProblemRepository"));
        System.out.println("GenerateNormalJobDetail started");
    }
}
