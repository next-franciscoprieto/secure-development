package com.beeva.mongodb.form;

import com.beeva.validation.Message;
import com.beeva.validation.Title;

import java.io.Serializable;

public class MessageForm implements Serializable {

	private static final long serialVersionUID = -6063993380423834607L;
	@Title
	private String title;
    @Message
	private String message;

	public MessageForm() {
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
