package com.sreeyainfotech.sampleasynchronous.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class OffersList implements Serializable{

    private String content;
    private String caturl;
    private String imagepath;
    private String category_id;
    private String code;

    public OffersList(JSONObject json) {
        try {
            content = json.getString("content");
            caturl = json.getString("caturl");
            imagepath = json.getString("imagepath");
            category_id = json.getString("category_id");
            code = json.getString("code");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCaturl() {
        return caturl;
    }

    public void setCaturl(String caturl) {
        this.caturl = caturl;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
