package com.beeva.mongodb.serviceImpl;

import com.beeva.mongodb.form.MessageData;
import com.beeva.mongodb.form.MessageForm;
import com.beeva.mongodb.form.RegisterForm;
import com.beeva.mongodb.model.Message;
import com.beeva.mongodb.model.User;
import com.beeva.mongodb.service.DataService;
import com.beeva.mongodb.service.Service;
import com.beeva.utils.SecUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Component
public class ServiceImpl implements Service {


    public static final String OPT_SECURE = "/opt/secure/";
    public static final int ROLE_USER = 2;
    /** Logger */
    private static Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

    private DataService dataService;

    @Autowired
    public ServiceImpl(DataService dataService) {

        this.dataService = dataService;
    }

    @Override
    public void registerUser(String imageName, BufferedImage image, RegisterForm inputData) throws IOException{

        String name = SecUtils.encryptSHA1(imageName + System.currentTimeMillis());

        File outputfile = new File(OPT_SECURE + name);
        ImageIO.write(image, "jpg", outputfile);

        User user = new User();
        user.setEmail(inputData.getEmail());
        user.setImage(name);
        user.setName(inputData.getName());
        user.setRole(ROLE_USER);
        user.setPassword(SecUtils.encryptSHA1(inputData.getPass()));

        dataService.saveUser(user);
    }

    @Override
    public void publishMessage(String userId, MessageForm inputData) {

        Message message = new Message();
        message.setTitle(inputData.getTitle());
        message.setBody(inputData.getMessage());
        message.setDate(new Date(System.currentTimeMillis()));
        message.setUserId(userId);

        dataService.saveMessage(message);
    }

    @Override
    public List<MessageData> getAllMessage (){

        List<Message> messages = dataService.getMessage();
        Calendar cal = Calendar.getInstance();

        List<MessageData> result = new ArrayList<>();

        for (Message message : messages){

            User user = dataService.loadUserByEmail(message.getUserId());

            cal.setTime(message.getDate());
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            MessageData sms = new MessageData();
            // FIXME Change to repair security bug
            sms.setTitle(SecUtils.encodeForHTML(message.getTitle()));
            sms.setBody(SecUtils.encodeForHTML(message.getBody()));
            sms.setImage(SecUtils.encodeForHTMLAttribute(user.getImage()));

//            sms.setTitle(message.getTitle());
//            sms.setBody(message.getBody());
//            sms.setImage(user.getImage());

            sms.setDay(String.valueOf(day));
            sms.setMonth(parseMonth(month));

            result.add(sms);

        }

        return result;
    }

    private String parseMonth(int month) {
        switch (month){
            case 0 : return "ENE";
            case 1 : return "FEB";
            case 2 : return "MAR";
            case 3 : return "ABR";
            case 4 : return "MAY";
            case 5 : return "JUN";
            case 6 : return "JUL";
            case 7 : return "AGO";
            case 8 : return "SEP";
            case 9 : return "OCT";
            case 10 : return "NOV";
            case 11 : return "DIC";
        }
        return "";
    }
}
