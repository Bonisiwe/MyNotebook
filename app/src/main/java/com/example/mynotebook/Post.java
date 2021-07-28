package com.example.mynotebook;

import java.util.Date;

public class Post {
    public String body, uid, pid;

    public Post() {
    }
    public Post(String body, String uid) {

        this.body = body;
        this.uid = uid;

    }

    public Post(String body, String uid, String pid) {

        this.body = body;
        this.uid = uid;
        this.pid = pid;

    }
}
