package com.beeva.mongodb.service;

import com.beeva.mongodb.model.Message;
import com.beeva.mongodb.model.User;

import java.util.List;

/**
 * This Interface is the services layer to Mongo DB Entities
 * 
 * @author beeva
 * 
 */
public interface DataService {

    User loadUserByEmail(String email);

    void saveUser (User user);

    void saveMessage(Message message);

    List<Message> getMessage ();
}
