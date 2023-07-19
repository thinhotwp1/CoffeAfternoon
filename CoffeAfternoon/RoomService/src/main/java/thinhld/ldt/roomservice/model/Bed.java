package thinhld.ldt.roomservice.model;

import jakarta.persistence.*;
import lombok.Data;

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

    // bed using, fixing, need fix
    @Column(name = "needFix")
    private boolean needFix = false;
    @Column(name = "isUsing")
    private boolean isUsing = false;
    @Column(name = "isFixing")
    private boolean isFixing = false;

    // more manager
    @Column(name = "isDelete")
    private boolean isDelete = false;
    @Column(name = "lastComing")
    private String lastComing; // lần cuối đến sử dụng bed này, sau này làm báo cáo
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
