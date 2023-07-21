package thinhld.ldt.userservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
@Table(name = "t_user" )
public class User {
    @Id
    @Column(name = "userName")
    private String userName;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "type")
    private int type;
    @Column(name = "branch")
    private String branch;

}
