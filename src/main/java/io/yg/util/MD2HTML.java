package io.yg.util;

import com.github.hui.quick.plugin.md.MarkDown2HtmlWrapper;
import com.github.hui.quick.plugin.md.entity.MarkdownEntity;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.options.MutableDataSet;
import com.youbenzi.mdtool.tool.MDTool;
import org.apache.commons.io.FileUtils;
import top.touchface.md2x.Md2x;

import java.io.*;

/**
 * Creat by GuoJF on 2019-04-14:21:30
 */


public class MD2HTML {

    public static void main(String[] args) throws Exception {

        test02();

    }


    public static String mdtohtmlWithFile(File mdfile) throws IOException {

        String s = mdtohtmlWithFile(new File("/Users/guojf/Hadoop.md"));

        System.out.println(s);

        File file = new File("/Users/guojf/Hadoop.html");
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.write(s);
        printWriter.flush();
        printWriter.close();


        return MDTool.markdown2Html(mdfile);

    }


    static void md2xToHtml() throws Exception {


        FileInputStream fileInputStream = new FileInputStream("/Users/guojf/Hadoop.md");

        byte[] bytes = new byte[1024];

        String content = "";

        int length = 0;


        while ((length = fileInputStream.read(bytes)) != -1) {

            content += new String(bytes, 0, length);

        }

        System.out.println(content);
        Md2x md2x = new Md2x();

        String parse = md2x.parse(content);
        System.out.println(parse);

        File file = new File("/Users/guojf/Hadoop.html");
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.write(parse);
        printWriter.flush();
        printWriter.close();

    }


    public static void md2htmlbyGJF() throws Exception {

        File file = new File("/Users/guojf/Hadoop.md");


        BufferedReader reader = new BufferedReader(new FileReader(file));

        int lineNum = 1;


        String tempLine  = null;

        while ((tempLine = reader.readLine())!=null){
            System.out.println(tempLine);




            lineNum++;
        }





    }



    public static void test01() throws IOException {

        MarkdownEntity markdownEntity = MarkDown2HtmlWrapper.ofFile("/Users/guojf/Hadoop.md");

        System.out.println(markdownEntity.toString());



    }


    public static void test02() throws IOException {

        MutableDataSet options = new MutableDataSet();

        // uncomment to set optional extensions
        //options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));

        // uncomment to convert soft-breaks to hard breaks
        //options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();




        // You can re-use parser and renderer instances
        Node document = parser.parse("");
        String html = renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
        System.out.println(html);


    }

}
