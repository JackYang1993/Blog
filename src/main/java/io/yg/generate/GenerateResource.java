package io.yg.generate;

import io.yg.generate.entity.Article;
import io.yg.util.MarkDown2HtmlWrapper;
import io.yg.util.MarkdownEntity;
import org.apache.commons.io.FileUtils;


import java.io.*;
import java.util.*;
import java.util.jar.JarFile;

/**
 * Creat by GuoJF on mac
 */
public class GenerateResource {


    public static void fileScanner(File file) {

        try {
            File[] listFiles = file.listFiles();


            for (File file1 : listFiles) {
                if (file1.isFile()) {

                    if (file1.getName().endsWith(".md")) {


                        System.out.println(file1.getName());
                        generateHtml(file1);

                    }

                }
                if (file1.isDirectory()) {

                    if (file1.getName().contains("asset")) {

                        for (File listFile : file1.listFiles()) {

                            FileUtils.copyFile(listFile, new File("/usr/share/nginx/html//assets/" + listFile.getName()));
                        }


                    }
                    fileScanner(file1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void generateHtml(File file) {

        try {
            MarkdownEntity markdownEntity = MarkDown2HtmlWrapper.ofFile(file.getAbsolutePath());

            // System.out.println(markdownEntity.toString());


            String html = markdownEntity.toString();


            InputStream beginfile = GenerateResource.class.getResourceAsStream("/blog/static/begin.tmp");


            String bengintext = "";

            byte[] bytes = new byte[1024];


            int length = 0;


            while ((length = beginfile.read(bytes)) != -1) {

                bengintext += new String(bytes, 0, length, "UTF-8");

            }
            beginfile.close();

            String midtext = bengintext + html;


            InputStream endfile = GenerateResource.class.getResourceAsStream("/blog/static/end.tmp");


            String endtext = "";

            byte[] endbytes = new byte[1024];


            int endlength = 0;


            while ((endlength = endfile.read(endbytes)) != -1) {

                endtext += new String(endbytes, 0, endlength, "UTF-8");

            }
            endfile.close();


            String finaltext = midtext + endtext;

            file.getName();

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("/usr/local/nginx/html/" + file.getName().substring(0, file.getName().length() - 3) + ".html")));

            bufferedWriter.write(finaltext);


            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void copyImage(String imagepath) {
        File file = new File(imagepath);
        try {
            FileUtils.copyFile(file, new File("/usr/local/nginx/html/assets/" + file.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void delFile(File file) {


        if (file.getName().endsWith(".md")) {

            File file1 = new File("/usr/local/nginx/html/" + file.getName().split(".")[0] + ".html");
            file1.deleteOnExit();
        }


    }


    public static void generateIndex() {


       Set set = new TreeSet();

        File filelist = new File("D:\\CloudStation\\CloudStation\\课程\\院校\\中原工\\2018下学期\\环境配置");


        for (File file : filelist.listFiles()) {

            if (file.getName().endsWith(".md")) {

                Long time = file.lastModified();

                set.add(new Article(file.getName(),time));



            }
        }


        set.forEach((a)->{
            System.out.println(a);
        });





    }


    public static void copyCSSResgource() {

        try {
            File file = new File("/usr/share/nginx/html/app/");


            boolean exists = file.exists();

            if (!exists) {
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/app.css"), new File("/usr/local/nginx/html/css/app.css"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/markdown.css"), new File("/usr/local/nginx/html/css/markdown.css"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
