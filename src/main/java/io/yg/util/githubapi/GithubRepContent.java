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
public class GithubRepContent {

    private String name;
    private String path;
    private String sha;
    private int size;
    private String url;
    private String html_url;
    private String git_url;
    private String download_url;
    private String type;
    private Links _links;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setPath(String path) {
         this.path = path;
     }
     public String getPath() {
         return path;
     }

    public void setSha(String sha) {
         this.sha = sha;
     }
     public String getSha() {
         return sha;
     }

    public void setSize(int size) {
         this.size = size;
     }
     public int getSize() {
         return size;
     }

    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    public void setHtml_url(String html_url) {
         this.html_url = html_url;
     }
     public String getHtml_url() {
         return html_url;
     }

    public void setGit_url(String git_url) {
         this.git_url = git_url;
     }
     public String getGit_url() {
         return git_url;
     }

    public void setDownload_url(String download_url) {
         this.download_url = download_url;
     }
     public String getDownload_url() {
         return download_url;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void set_links(Links Links) {
         this._links = Links;
     }
     public Links get_links() {
         return _links;
     }

    @Override
    public String toString() {
        return "GithubRepContent{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", sha='" + sha + '\'' +
                ", size=" + size +
                ", url='" + url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", git_url='" + git_url + '\'' +
                ", download_url='" + download_url + '\'' +
                ", type='" + type + '\'' +
                ", _links=" + _links +
                '}';
    }
}