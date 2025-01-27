package com.sampleProject.bookMyShowApp.helper;

import com.sampleProject.bookMyShowApp.entities.Users;
import com.sampleProject.bookMyShowApp.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class UserToResponse {
    public static List<UserResponse> convertList(List<Users> users){
        List<UserResponse> allUserResponses=new ArrayList<>();
        for(Users u:users){
            allUserResponses.add(new UserResponse(u.getName(),u.getAge(),u.getCity()));
        }
        return allUserResponses;
    }

    public static UserResponse convertEntity(Users users){
        return new UserResponse(users.getName(),users.getAge(),users.getCity());
    }
}
