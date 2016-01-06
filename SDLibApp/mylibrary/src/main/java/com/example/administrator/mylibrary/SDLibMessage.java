package com.example.administrator.mylibrary;

/**
 * Created by Administrator on 2015-12-27.
 */
public class SDLibMessage {
    private String msg;

    public SDLibMessage(String message) {
        this.msg = message;
    }

    public String getMessage() {
        return "Message from LibProject : " + this.msg;
    }
}
