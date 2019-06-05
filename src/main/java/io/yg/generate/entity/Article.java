package io.yg.generate.entity;

import java.util.Comparator;

/**
 * Create by GuoJF on 2019/4/19
 */
public class Article  {


    private String name;
    private Long time;

    public Article(String name, Long time) {
        this.name = name;
        this.time = time;
    }

    public Article() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }




    @Override
    public String toString() {
        return "Article{" +
                "name='" + name + '\'' +
                ", time=" + time +
                '}';
    }


}
