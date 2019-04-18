package io.yg.generate;

import io.yg.util.MarkDown2HtmlWrapper;
import io.yg.util.MarkdownEntity;
import org.apache.commons.io.FileUtils;


import java.io.*;
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

                            FileUtils.copyFile(listFile, new File("./src/main/webapp/static/assets/" + listFile.getName()));
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


    }

}
