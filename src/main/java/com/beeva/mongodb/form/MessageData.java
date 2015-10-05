package com.beeva.mongodb.form;

import java.io.Serializable;

public class MessageData implements Serializable {

	private static final long serialVersionUID = -6063993380423834607L;


	private String title;
	private String body;
    private String image;
    private String day;
    private String month;

	public MessageData() {
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
