package com.beeva.mongodb.serviceImpl;

import com.beeva.mongodb.model.User;
import com.beeva.mongodb.service.DataService;
import com.beeva.mongodb.service.MongoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class MongoDataServiceImpl implements DataService {

    /** Logger */
    private static Logger logger = LoggerFactory.getLogger(MongoDataServiceImpl.class);

    /** Mongo Spring Template Object Mapper */
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoDataServiceImpl(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User loadUserByEmail(String email) {

        return (User) loadDataById(User.class, email);
    }

    private MongoEntity loadDataById(Class<?> clazz, String id)  {

        logger.debug("Load Data in " + clazz + " id:" + id);
        return (MongoEntity) mongoTemplate.findById(id, clazz);
    }

}
