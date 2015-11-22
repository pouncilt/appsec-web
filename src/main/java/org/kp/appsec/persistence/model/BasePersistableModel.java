package org.kp.appsec.persistence.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by tonte on 7/5/15.
 */
@MappedSuperclass
public class BasePersistableModel implements PersistableModel {
    @NotNull
    @Size(max = 25)
    @Basic(optional = false)
    @Column(updatable = false)
    private String createdBy;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.Generated(org.hibernate.annotations.GenerationTime.ALWAYS)
    @Column(updatable = false, insertable = false/*, nullable = false*/)
    @Type(type="dateTimeUserType")
    //@CreationTimestamp
    private Date createdDate/* = new Date()*/;

    @NotNull
    @Size(max = 25)
    @Basic(optional = false)
    private String lastModifiedBy;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.Generated(org.hibernate.annotations.GenerationTime.ALWAYS)
    @Column(updatable = false, insertable = false/*, nullable = false*/)
    @Type(type="dateTimeUserType")
    //@UpdateTimestamp
    private Date lastModifiedDate;

    protected BasePersistableModel() {
        super();
    }

    public BasePersistableModel(String createdBy, String lastModifiedBy){
        super();
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
    }

    public BasePersistableModel(String createdBy){
        super();
        this.createdBy = this.lastModifiedBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
        this.lastModifiedDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedDate = new Date();
    }
}
