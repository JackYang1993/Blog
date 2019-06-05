package io.yg.generate;

import io.yg.generate.entity.Article;
import io.yg.util.MarkDown2HtmlWrapper;
import io.yg.util.MarkdownEntity;
import org.apache.commons.io.FileUtils;


import java.io.*;
import java.util.*;

/**
 * Creat by GuoJF on mac
 */
public class GenerateResource {


    public static void fileScanner(File file) {
        new Thread() {
            @Override
            public void run() {
                try {

                   // Thread.sleep(20000);

                    File[] listFiles = file.listFiles();
                    for (File file1 : listFiles) {
                        if (file1.isFile()) {

                            if (file1.getName().endsWith(".md") && !file1.getName().equals("README.md")) {

                                System.out.println(file1.getName());
                                generateHtml(file1);

                            }

                        }
                        if (file1.isDirectory()) {

                            if (file1.getName().contains("assets")) {

                                for (File listFile : file1.listFiles()) {

                                    FileUtils.copyFile(listFile, new File("/usr/share/nginx/html/assets/" + listFile.getName()));
                                }


                            }
                            fileScanner(file1);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }.start();


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

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("/usr/share/nginx/html/" + file.getName().substring(0, file.getName().length() - 3) + ".html")));

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


        new Thread() {
            @Override
            public void run() {
                try {

                    List<Article> articles = new ArrayList<>();

                    File filelist = new File("/home/blog/ProblemRepository");


                    for (File file : filelist.listFiles()) {


                        if (file.getName().contains(".md") && !file.getName().contains("index.html") && !file.getName().contains("README.md")) {

                            Long time = file.lastModified();

                          //  System.out.println(file.getName());
                            articles.add(new Article(file.getName(), time));

                           // System.out.println(file.getName());

                        }
                    }


                    String tmp01 = "<a href=\"";
                    String tmp02 = "\" class=\"post__link\">\n" +
                            "\n" +
                            "<article class=\"post\">\n" +
                            "<h2 class=\"post__title\">";
                    String tmp03 = "</h2>\n" +
                            "</article>\n" +
                            "</a>";


                    String tmp = "";

                    Collections.sort(articles, new Comparator<Article>() {
                        @Override
                        public int compare(Article article01, Article article02) {
                            if (article01.getTime()>article02.getTime()){
                                return 1;
                            }else {
                                return -1;
                            }
                        }
                    });
                    for (Article article : articles) {

                       /* String tmp000= "Fl.md";

                        String[] split = tmp000.split(".");


                        String s = article.getName().split(".")[0];
                        System.out.println(s);*/


                        tmp += tmp01 + "/" + article.getName().split("\\.")[0] + ".html" + tmp02 + article.getName().split("\\.")[0] + tmp03;

                        System.out.println(article.getName());

                    }


                    InputStream beginfile = GenerateResource.class.getResourceAsStream("/blog/static/begin.tmp");


                    String bengintext = "";

                    byte[] bytes = new byte[1024];


                    int length = 0;


                    while ((length = beginfile.read(bytes)) != -1) {

                        bengintext += new String(bytes, 0, length, "UTF-8");

                    }
                    beginfile.close();

                    String midtext = bengintext + tmp;


                    InputStream endfile = GenerateResource.class.getResourceAsStream("/blog/static/end.tmp");


                    String endtext = "";

                    byte[] endbytes = new byte[1024];


                    int endlength = 0;


                    while ((endlength = endfile.read(endbytes)) != -1) {

                        endtext += new String(endbytes, 0, endlength, "UTF-8");

                    }
                    endfile.close();


                    String finaltext = midtext + endtext;


                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("/usr/share/nginx/html/index.html")));

                    bufferedWriter.write(finaltext);
                    bufferedWriter.flush();
                    bufferedWriter.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }


    public static void copyCSSResgource() {

        try {
            File file = new File("/usr/share/nginx/html/app/");


            boolean exists = file.exists();

            if (!exists) {

                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/css/app.css"), new File("/usr/share/nginx/html/css/app.css"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/css/markdown.css"), new File("/usr/share/nginx/html/css/markdown.css"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/FontAwesome.otf"), new File("/usr/share/nginx/html/fonts/FontAwesome.otf"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.eot"), new File("/usr/share/nginx/html/fonts/fontawesome-webfont.eot"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.svg"), new File("/usr/share/nginx/html/fonts/fontawesome-webfont.svg"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.ttf"), new File("/usr/share/nginx/html/fonts/fontawesome-webfont.ttf"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.woff"), new File("/usr/share/nginx/html/fonts/fontawesome-webfont.woff"));
                FileUtils.copyInputStreamToFile(GenerateResource.class.getResourceAsStream("/blog/fonts/fontawesome-webfont.woff2"), new File("/usr/share/nginx/html/fonts/fontawesome-webfont.woff2"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static Boolean isMDorImage(File file) {

        if (file.isDirectory()) return false;


        String fileName = file.getName();


        if (!fileName.contains(".md") || !file.getParentFile().getName().contains("assets")) return false;


        return true;

    }


}
