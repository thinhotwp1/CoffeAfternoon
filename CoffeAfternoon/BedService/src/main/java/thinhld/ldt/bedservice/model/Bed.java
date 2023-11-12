package thinhld.ldt.bedservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@Entity
@NoArgsConstructor
@Table(name = "t_bed"
        , indexes = {@Index(name = "idx_bed", columnList = "id")}
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bed extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "room_id")
    private long roomId;
    @Column(name = "bedName")
    private String bedName;
    // bed using, fixing, need fix
    @Column(name = "dateTicket")
    private Calendar dateTicket;
    @Column(name = "isFixing")
    private boolean isFixing = false;
    @Column(name = "isUsing")
    private boolean isUsing = false;
}
