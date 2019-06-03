package io.yg.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.JSONToken;
import io.yg.util.githubapi.GithubAPI;
import io.yg.util.githubapi.GithubRepContent;
import org.apache.commons.io.CopyUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.FileCopyUtils;

import java.util.Date;
import java.util.List;

/**
 * Creat by GuoJF on mac
 */
public class NetWorkUtil {


    private static Date date;


    static {


        date = new Date();


    }


    public static Boolean isRepUpdate(String url) {

        try {
            // 创建Httpclient对象
            CloseableHttpClient httpclient = HttpClients.createDefault();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(url);


            CloseableHttpResponse httpResponse = httpclient.execute(httpGet);


            HttpEntity entity = httpResponse.getEntity();

            String content = EntityUtils.toString(entity);


            GithubAPI githubAPI = JSON.parseObject(content, new TypeReference<GithubAPI>() {
            });

            Date updated_at = githubAPI.getUpdated_at();


            System.out.println("服务器更新时间：" + updated_at);


            if (updated_at.compareTo(date) < 0) {

                date = updated_at;
                return true;
            }

            if (updated_at.compareTo(date) > 0) {

                date = updated_at;

                return true;
            }

            System.out.println("本地记录更新时间：" + date);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;


    }


    public static List<GithubRepContent> getAllFile(String rooturl, String imageurl) throws Exception {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 创建http GET请求
        HttpGet httpGet = new HttpGet(rooturl);


        /*
         * 处理根目录相关资源
         * */
        CloseableHttpResponse httpResponse = httpclient.execute(httpGet);


        HttpEntity entity = httpResponse.getEntity();

        String content = EntityUtils.toString(entity);


        List<GithubRepContent> githubRepContents = JSON.parseObject(content, new TypeReference<List<GithubRepContent>>() {
        });

        for (GithubRepContent repContent : githubRepContents) {

            System.out.println(repContent);
        }


        /*
         * 处理图片相关资源
         * */

        HttpGet httpGet1 = new HttpGet(imageurl);

        CloseableHttpResponse image = httpclient.execute(httpGet1);

        HttpEntity imageEntity = image.getEntity();

        String imagejson = EntityUtils.toString(imageEntity);


        List<GithubRepContent> imagejsons = JSON.parseObject(imagejson, new TypeReference<List<GithubRepContent>>() {
        });

        for (GithubRepContent repContent : imagejsons) {

            System.out.println(repContent);
        }

        httpclient.close();


        githubRepContents.addAll(imagejsons);


        return githubRepContents;

    }





}
