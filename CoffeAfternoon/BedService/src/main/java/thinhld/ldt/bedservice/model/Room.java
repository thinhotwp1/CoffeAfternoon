package thinhld.ldt.bedservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "t_room"
        , indexes = {@Index(name = "idx_room", columnList = "id")}
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Room extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "roomName")
    private String roomName;
    @Column(name = "roomType")
    private String roomType;
    @Column(name = "isActive")
    private boolean isActive = true;
    // one room to many bed
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "room_id")
    private List<Bed> beds;

}
