package com.example.jinux.thirtydays.bean;

/**
 * Created by jinux on 15-4-6.
 */
public class PlanItem {
    public long id;
    public String name;
    public String startTime;
    public String endTime;
    public String progressDay;
    public String description;

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
}
