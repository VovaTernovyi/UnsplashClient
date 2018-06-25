package com.example.owner.unsplashclient.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UnsplashEntity implements Serializable {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("color")
    @Expose
    public String color;
    @SerializedName("description")
    @Expose
    public Object description;
    @SerializedName("urls")
    @Expose
    public UrlsEntity urls;
    @SerializedName("links")
    @Expose
    public LinkEntity links;
    @SerializedName("categories")
    @Expose
    public List<Object> categories = null;
    @SerializedName("sponsored")
    @Expose
    public Boolean sponsored;
    @SerializedName("likes")
    @Expose
    public Integer likes;
    @SerializedName("liked_by_user")
    @Expose
    public Boolean likedByUser;
    @SerializedName("current_user_collections")
    @Expose
    public List<Object> currentUserCollections = null;
    @SerializedName("slug")
    @Expose
    public Object slug;
    @SerializedName("user")
    @Expose
    public UserEntity user;

    public UnsplashEntity() {

    }

    public UnsplashEntity(String id, String createdAt, String updatedAt, Integer width,
                          Integer height, String color, Object description, UrlsEntity urls, LinkEntity links,
                          List<Object> categories, Boolean sponsored, Integer likes, Boolean likedByUser,
                          List<Object> currentUserCollections, Object slug, UserEntity user) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.width = width;
        this.height = height;
        this.color = color;
        this.description = description;
        this.urls = urls;
        this.links = links;
        this.categories = categories;
        this.sponsored = sponsored;
        this.likes = likes;
        this.likedByUser = likedByUser;
        this.currentUserCollections = currentUserCollections;
        this.slug = slug;
        this.user = user;
    }

    public String getDateFormatted(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = new Date();
        try {
           date = dateFormat.parse(createdAt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
}
