package thinhld.ldt.roomservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_room"
        , indexes = {@Index(name = "idx_phoneNumber", columnList = "phoneNumber")}
)
public class Room {
    @Id
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "roomName")
    private String roomName;
    @Column(name = "type")
    private int type;
    @Column(name = "isDelete")
    private boolean isDelete = false;
    @Column(name = "lastComing")
    private String lastComing ; // lần cuối đến sử dụng dịch vụ, sau này làm báo cáo
    @Column(name = "lastUpdate")
    private String lastUpdate ;
    @Column(name = "userCurrent")
    private String userCurrent ;

    public Room() {
    }

    public Room(String phoneNumber, String roomName, int type) {
        this.phoneNumber = phoneNumber;
        this.roomName = roomName;
        this.type = type;
    }

}
