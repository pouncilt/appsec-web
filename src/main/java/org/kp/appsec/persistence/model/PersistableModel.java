package org.kp.appsec.persistence.model;

import java.util.Date;

/**
 * Created by tonte on 7/5/15.
 */
public interface PersistableModel {
    String getCreatedBy();
    //void setCreatedBy(String createdBy);
    Date getCreatedDate();
    //void setCreatedDate(Date createDate);
    Date getLastModifiedDate();
    //void setLastModifiedDate(Date lastModifiedDate);
}
