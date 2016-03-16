package com.sda.frame.model;

import java.io.Serializable;

/**
 * Created by scorpio on 16/3/2.
 */
public class NewsData implements Serializable {

    /**
     * id : 674
     * article_id : 479779
     * title : ThinkGeek出售1:1《星球大战》帝国冲锋队头盔
     * date : 2016-03-02 10:29:36
     * intro : 据外媒报道，近日专门出售创意产品的趣味电商ThinkGeek结合星球大战元素推出了全新的1:1帝国冲锋队头盔复制品——First Order Stormtrooper 。ThinkGeek表示，这款头盔是根据电影《星球大战7：原力觉醒》中头盔道具的扫描结果制作而成，由轻型塑料材质打造。
     * topic : //cnbeta1.sinaapp.com/topics/327e9d7748ef1e7.png
     * view_num : 28
     * comment_num : 0
     * source : cnBeta.COM
     * source_link : http://www.cnbeta.com/
     * hot : 0
     * pushed : 0
     */

    private int id;
    private int article_id;
    private String title;
    private String date;
    private String intro;
    private String topic;
    private int view_num;
    private int comment_num;
    private String source;
    private String source_link;
    private int hot;
    private int pushed;

    public void setId(int id) {
        this.id = id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setView_num(int view_num) {
        this.view_num = view_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setSource_link(String source_link) {
        this.source_link = source_link;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public void setPushed(int pushed) {
        this.pushed = pushed;
    }

    public int getId() {
        return id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getIntro() {
        return intro;
    }

    public String getTopic() {
        return topic;
    }

    public int getView_num() {
        return view_num;
    }

    public int getComment_num() {
        return comment_num;
    }

    public String getSource() {
        return source;
    }

    public String getSource_link() {
        return source_link;
    }

    public int getHot() {
        return hot;
    }

    public int getPushed() {
        return pushed;
    }
}
