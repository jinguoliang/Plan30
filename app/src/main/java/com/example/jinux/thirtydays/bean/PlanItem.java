package com.example.jinux.thirtydays.bean;

/**
 * Created by jinux on 15-4-6.
 */
public class PlanItem {
    private long id;
    private String name;
    private String startTime;
    private String endTime;
    private String progressDay;
    private String description;
    private int todayReview;
    private String todaySummary;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProgressDay() {
        return progressDay;
    }

    public void setProgressDay(String progressDay) {
        this.progressDay = progressDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTodayReview() {
        return todayReview;
    }

    public void setTodayReview(int todayReview) {
        this.todayReview = todayReview;
    }

    public String getTodaySummary() {
        return todaySummary;
    }

    public void setTodaySummary(String todaySummary) {
        this.todaySummary = todaySummary;
    }
}
