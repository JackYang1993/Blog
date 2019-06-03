package io.yg.generate;

import io.yg.util.NetWorkUtil;
import io.yg.util.githubapi.ArticleUrl;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Create by GuoJF on 2019/4/19
 */
public class Test {

    public static void main(String[] args) throws Exception {


        //FileUtils.copyURLToFile(new URL("https://raw.githubusercontent.com/GuoJiafeng/ProblemRepository/master/Github%E7%94%9F%E6%88%90%E6%B7%BB%E5%8A%A0SSH%E5%85%AC%E9%92%A5.md"),new File("/Users/guojf/1.md"));



        NetWorkUtil.getAllFile(ArticleUrl.ARTICLEURL_ROOT,ArticleUrl.ARTICLEURL_IMAGE);


    }
}
