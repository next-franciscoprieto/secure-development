package com.beeva.mongodb.service;

import com.beeva.mongodb.form.MessageData;
import com.beeva.mongodb.form.MessageForm;
import com.beeva.mongodb.form.RegisterForm;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * This Interface is the services layer to Mongo DB Entities
 * 
 * @author beeva
 * 
 */
public interface Service {

    void registerUser(String imageName, BufferedImage image, RegisterForm inputData) throws IOException;

    void publishMessage(String userId, MessageForm inputData);

    List<MessageData> getAllMessage ();
}
