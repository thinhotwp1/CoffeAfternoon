package thinhld.ldt.bedservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {

    @Basic
    @JsonIgnore
    @Column(name = "CREATE_DATE", updatable = false)
    protected Date createDate;

    @CreatedBy
    @Basic
    @JsonIgnore
    @Column(name = "CREATE_BY", updatable = false)
    protected String createBy;

    @Basic
    @JsonIgnore
    @Column(name = "UPDATE_DATE")
    protected Date updateDate;

    @LastModifiedBy
    @Basic
    @JsonIgnore
    @Column(name = "UPDATE_BY")
    protected String updateBy;

    @PrePersist
    protected void prePersist() {
        this.createDate = new Date();
        this.updateDate = new Date();
        String username = "admin";
        this.createBy = username;
        this.updateBy = username;
    }

    @PreUpdate
    private void preUpdate() {
        this.updateDate = new Date();
        this.updateBy = "admin";
    }
}
