package com.beeva.mongodb.service;

import com.beeva.mongodb.model.User;

/**
 * This Interface is the services layer to Mongo DB Entities
 * 
 * @author beeva
 * 
 */
public interface DataService {

    User loadUserByEmail(String email);
}
