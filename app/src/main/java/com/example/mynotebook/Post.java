package com.example.mynotebook;

import java.util.Date;

public class Post {
    public String body, uid, postKey, topic;

    public Post() {
    }

    public Post(String body, String uid, String topic) {

        this.body = body;
        this.uid = uid;
        this.topic = topic;
    }

    public Post(String body, String uid, String pid, String topic) {

        this.body = body;
        this.uid = uid;
        this.postKey = pid;
        this.topic = topic;
    }

    public String getBody() {
        return body;
    }

    public String getUid() {
        return uid;
    }

    public String getPostKey() {
        return postKey;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }
}
