package com.osama.lifeshare;

public class ForumSupport {

    private String Name,Feedback;

    public ForumSupport()
    {

    }

    public ForumSupport(String name, String feedback) {
        Name = name;
        Feedback = feedback;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }
}
