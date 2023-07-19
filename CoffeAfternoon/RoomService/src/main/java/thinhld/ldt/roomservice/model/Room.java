package thinhld.ldt.roomservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "t_room"
        , indexes = {@Index(name = "idx_room", columnList = "id")}
)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "roomName")
    private String roomName;
    @Column(name = "bedNumber")
    private int bedNumber;
    @Column(name = "isActive")
    private boolean isActive = true;
    // one room to many bed
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "room_id")
    private List<Bed> beds;

    // more manager
    @Column(name = "isDelete")
    private boolean isDelete = false;
    @Column(name = "lastUpdate")
    private String lastUpdate ;
    @Column(name = "userCurrent")
    private String userCurrent ;

    public Room() {
    }

    public Room(String roomName, int bedNumber) {
        this.roomName = roomName;
        this.bedNumber = bedNumber;
    }
}
