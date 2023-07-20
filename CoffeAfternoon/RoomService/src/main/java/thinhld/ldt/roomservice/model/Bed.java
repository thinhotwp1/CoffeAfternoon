package thinhld.ldt.roomservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Data
@Entity
@Table(name = "t_bed"
        , indexes = {@Index(name = "idx_bed", columnList = "id")}
)
public class Bed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "room_id")
    private long roomId;
    @Column(name = "bedName")
    private String bedName;
    @Column(name = "customerId")
    private String customerId;

    // bed using, fixing, need fix
    @Column(name = "dateTicket")
    private Calendar dateTicket;
    @Column(name = "needFix")
    private boolean needFix = false;
    @Column(name = "isUsing")
    private boolean isUsing = false;
    @Column(name = "isFixing")
    private boolean isFixing = false;

    // more manager
    @Column(name = "isDelete")
    private boolean isDelete = false;
    @Column(name = "lastUpdate")
    private String lastUpdate;
    @Column(name = "userCurrent")
    private String userCurrent;

    public Bed() {
    }

    public Bed(long roomId, String bedName) {
        this.roomId = roomId;
        this.bedName = bedName;
    }
}
