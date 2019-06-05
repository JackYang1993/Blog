package io.yg.generate;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.concurrent.TimeUnit;


/**
 * Creat by GuoJF on mac
 */
public class FileScanner extends FileAlterationListenerAdaptor {
    private Logger log = Logger.getLogger(FileScanner.class);

    /**
     * 文件创建执行
     */
    public void onFileCreate(File file) {
        log.info("[新建]:" + file.getAbsolutePath());

        GenerateResource.fileScanner(new File("/home/blog/ProblemRepository"));

        GenerateResource.generateIndex();
    }

    /**
     * 文件创建修改
     */
    public void onFileChange(File file) {

        log.info("[修改]:" + file.getAbsolutePath());

        GenerateResource.fileScanner(new File("/home/blog/ProblemRepository"));

        GenerateResource.generateIndex();


    }

    /**
     * 文件删除
     */
    public void onFileDelete(File file) {
        if (file.isFile()) {
            log.info("[删除]:" + file.getAbsolutePath());
            GenerateResource.delFile(new File(file.getAbsolutePath()));
        }
    }

    /**
     * 目录创建
     */
    public void onDirectoryCreate(File directory) {
        log.info("[新建]:" + directory.getAbsolutePath());
    }

    /**
     * 目录修改
     */
    public void onDirectoryChange(File directory) {
        log.info("[修改]:" + directory.getAbsolutePath());
    }

    /**
     * 目录删除
     */
    public void onDirectoryDelete(File directory) {
        log.info("[删除]:" + directory.getAbsolutePath());
    }

    public void onStart(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        super.onStart(observer);
    }

    public void onStop(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        super.onStop(observer);
    }

    public void start(String filepath) throws Exception {

        // 监控目录
        String rootDir = filepath;
        // 轮询间隔 5 秒
        long interval = TimeUnit.SECONDS.toMillis(1);
        // 创建过滤器  暂不使用
        IOFileFilter directories = FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                FileFilterUtils.suffixFileFilter(".git"));

        IOFileFilter files = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(".md"));

        IOFileFilter filter = FileFilterUtils.or(directories);


        // 使用过滤器
        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
        //不使用过滤器
        //FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
        observer.addListener(new FileScanner());
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        monitor.start();

    }

}