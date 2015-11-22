package org.kp.appsec.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by tonte on 7/1/15.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Services")
public abstract class BaseService extends BaseEntity{
    @NotNull
    @Size(max = 50)
    @Column(nullable = false)
    private String name;

    protected BaseService() {
        super();
    }

    protected BaseService(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
