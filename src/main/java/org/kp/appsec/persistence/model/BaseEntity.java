package org.kp.appsec.persistence.model;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.kp.appsec.persistence.adapter.DateTimeUserType;

import javax.persistence.*;

/**
 * Created by tonte on 7/1/15.
 */
@MappedSuperclass
@TypeDefs({
        @TypeDef(name="dateTimeUserType", typeClass=DateTimeUserType.class)
})
public abstract class BaseEntity extends BasePersistableModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    protected BaseEntity() {
        super();
    }

    public BaseEntity(String createdBy, String lastModifiedBy) {
        super(createdBy, lastModifiedBy);
    }

    public BaseEntity(String createdBy) {
        super(createdBy);
    }

    public Long getId() {
        return id;
    }


}
