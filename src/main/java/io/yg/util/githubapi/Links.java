/**
  * Copyright 2019 bejson.com 
  */
package io.yg.util.githubapi;

/**
 * Auto-generated: 2019-04-20 18:27:9
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Links {

    private String self;
    private String git;
    private String html;
    public void setSelf(String self) {
         this.self = self;
     }
     public String getSelf() {
         return self;
     }

    public void setGit(String git) {
         this.git = git;
     }
     public String getGit() {
         return git;
     }

    public void setHtml(String html) {
         this.html = html;
     }
     public String getHtml() {
         return html;
     }

    @Override
    public String toString() {
        return "Links{" +
                "self='" + self + '\'' +
                ", git='" + git + '\'' +
                ", html='" + html + '\'' +
                '}';
    }
}