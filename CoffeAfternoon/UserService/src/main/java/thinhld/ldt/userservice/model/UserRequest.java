package thinhld.ldt.userservice.model;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class UserRequest {
    private String name;
    private String userName;
    private String password;
    private int type;
    private String branch;
    public User convertDTO(UserRequest userRequest){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userRequest,User.class);
    }

}
