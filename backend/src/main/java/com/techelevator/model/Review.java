package com.techelevator.model;

public class Review {

    private long id;
    private String name;
    private String title;
    private String text;
    private int starCount;
    private String type;
    private long userId;
    private long targetId;
    private String targetName;

    public Review(long id, String name, String title, String text, int starCount, String type, long userId, long targetId) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.text = text;
        this.starCount = starCount;
        this.type = type;
        this.userId = userId;
        this.targetId = targetId;
    }

    public Review() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}
