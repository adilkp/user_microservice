package com.adil.user.service.impl;

import com.adil.user.service.entities.Rating;
import com.adil.user.service.entities.User;
import com.adil.user.service.exceptions.ResourceNotFoundException;
import com.adil.user.service.repositories.UserRepository;
import com.adil.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {

        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given Id is not found on server : "+userId));

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given Id is not found on server : "+userId));

        //we call rating microservice to get the ratings by that particular user

        //http://localhost:8083/ratings/users/7f35bda6-45a7-4ca1-9fd5-4528a1520311

        ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);

        logger.info("{} ",ratingsOfUser);

        user.setRatings(ratingsOfUser);

        return user;
      }
}
