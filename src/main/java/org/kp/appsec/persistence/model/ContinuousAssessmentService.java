package org.kp.appsec.persistence.model;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import java.util.Set;

/**
 * Created by tonte on 7/1/15.
 */
public class ContinuousAssessmentService extends BaseService {
    @Valid
    @ElementCollection
    @CollectionTable(name="ServiceCredentials")
    @OrderBy("name, version DESC")
    @Column(nullable = false)
    private Set<Credential> credentials;

    public ContinuousAssessmentService() {
        super();
    }

    public ContinuousAssessmentService(Set<Credential> credentials) {
        super();
        this.credentials = credentials;
    }

    public Set<Credential> getCredentials() {
        return credentials;
    }

    public void setCredentials(Set<Credential> credentials) {
        this.credentials = credentials;
    }
}
