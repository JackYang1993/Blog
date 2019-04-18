package io.yg.util;

import com.google.common.base.Joiner;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by GuoJF on 2019/4/18
 */
public class FileReadUtil {
    public static String readAll(String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
        List<String> lines = reader.lines().collect(Collectors.toList());
        return Joiner.on("\n").join(lines);
    }


}
