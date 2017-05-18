package com.example.arind.leasingmanager;

/**
 * Created by arind on 18-05-2017.
 */

public class POJO {

    String name;
    String uid;
    String address_id;
    String discussion_id;
    String message;

    public POJO(String uid, String address_id, String discussion_id, String message, String name) {
        this.address_id = address_id;
        this.uid = uid;
        this.discussion_id = discussion_id;
        this.message = message;
        this.name = name;
    }

    public POJO()   {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getDiscussion_id() {
        return discussion_id;
    }

    public void setDiscussion_id(String discussion_id) {
        this.discussion_id = discussion_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
