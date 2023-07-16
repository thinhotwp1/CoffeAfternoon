package thinhld.ldt.userservice.model;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String user;
    private String pass;
    private int type;

    public User convertDTO(UserRequest request){
        User user1 = new User();
        user1.setUserName(request.getUser());
        user1.setName(request.getName());
        user1.setPassword(request.getPass());
        user1.setType(request.getType());
        return user1;
    }

}
