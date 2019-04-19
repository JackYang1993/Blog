package io.yg.generate.entity;

/**
 * Create by GuoJF on 2019/4/19
 */
public class Article implements Comparable<Article> {


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
    public int compareTo(Article o) {
        Article p = (Article) o;

        return this.time.compareTo(p.time);
    }

    @Override
    public String toString() {
        return "Article{" +
                "name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}
