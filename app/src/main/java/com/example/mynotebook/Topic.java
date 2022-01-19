package com.example.mynotebook;

public class Topic {
    public String topic, tid;
    public Topic() {
    }
    public Topic(String topic){
        this.topic = topic;
    }
    public Topic(String topic, String tid) {
        this.topic = topic;
        this.tid = tid;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setTopicID(String topicID) {this.tid = tid;}

    public String getTopic() {
        return topic;
    }

    public String getTopicID() {
        return tid;
    }


}
