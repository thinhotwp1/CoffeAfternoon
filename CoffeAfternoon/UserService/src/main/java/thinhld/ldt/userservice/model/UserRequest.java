package thinhld.ldt.userservice.model;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class UserRequest {
    private String name;
    private String user;
    private String pass;
    private int type;
    public User convertDTO(UserRequest userRequest){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userRequest,User.class);
    }

}
