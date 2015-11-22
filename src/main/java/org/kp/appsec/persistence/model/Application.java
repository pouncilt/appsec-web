package org.kp.appsec.persistence.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tonte on 7/1/15.
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Applications")
public class Application extends BaseEntity {
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected ApplicationType type;
    @NotNull
    @Size(min = 2, max = 500)
    @Column(nullable = false)
    protected String sourceCodeRepositoryUrl;
    @NotNull
    @Size(max = 250)
    @Column(nullable = false)
    protected String sourceCodeRepositoryName;
    @org.hibernate.annotations.Type(type = "yes_no")
    protected Boolean hasDedicatedBuildBox;
    @Valid
    @ElementCollection
    @CollectionTable(name="IntergratedDevelopmentEnvironments")
    @OrderBy("name, version DESC")
    protected Set<IdeInfo> ideInfoList = new HashSet<IdeInfo>();
    @Valid
    @ElementCollection
    @CollectionTable(name="BuildTools", joinColumns = @JoinColumn(name="APP_ID"))
    @Column(name="NAME", nullable = false)
    protected Set<String> buildTools = new HashSet<String>();
    @Size(max = 250)
    protected String buildEnvironment;
    @Valid
    @ElementCollection
    @CollectionTable(name="ApplicationCredentials")
    @OrderBy("USERNAME, version DESC")
    protected Set<Credential> credentials = new HashSet<Credential>();
    @Size(max = 100)
    protected String language;
    @Valid
    @ElementCollection
    @CollectionTable(name="Comments", joinColumns = @JoinColumn(name="APP_ID"))
    @Column(name="COMMENT", nullable = false)
    protected List<String> commments = new ArrayList<String>();
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    protected Set<BaseService> services;

    protected Application () {
        super();
    }

    public Application(String createdBy) {
        super(createdBy);
    }

    public ApplicationType getType() {
        return type;
    }

    public void setType(ApplicationType type) {
        this.type = type;
    }

    public String getSourceCodeRepositoryUrl() {
        return sourceCodeRepositoryUrl;
    }

    public void setSourceCodeRepositoryUrl(String sourceCodeRepositoryUrl) {
        this.sourceCodeRepositoryUrl = sourceCodeRepositoryUrl;
    }

    public String getSourceCodeRepositoryName() {
        return sourceCodeRepositoryName;
    }

    public void setSourceCodeRepositoryName(String sourceCodeRepositoryName) {
        this.sourceCodeRepositoryName = sourceCodeRepositoryName;
    }

    public Boolean getHasDedicatedBuildBox() {
        return hasDedicatedBuildBox;
    }

    public void setHasDedicatedBuildBox(Boolean hasDedicatedBuildBox) {
        this.hasDedicatedBuildBox = hasDedicatedBuildBox;
    }

    public Set<IdeInfo> getIdeInfoList() {
        return ideInfoList;
    }

    public void setIdeInfoList(Set<IdeInfo> ideInfoList) {
        this.ideInfoList = ideInfoList;
    }

    public Set<String> getBuildTools() {
        return buildTools;
    }

    public void setBuildTools(Set<String> buildTools) {
        this.buildTools = buildTools;
    }

    public String getBuildEnvironment() {
        return buildEnvironment;
    }

    public void setBuildEnvironment(String buildEnvironment) {
        this.buildEnvironment = buildEnvironment;
    }

    public Set<Credential> getCredentials() {
        return credentials;
    }

    public void setCredentials(Set<Credential> credentials) {
        this.credentials = credentials;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getCommments() {
        return commments;
    }

    public void setCommments(List<String> commments) {
        this.commments = commments;
    }

    public Set<BaseService> getServices() {
        return services;
    }

    public void setServices(Set<BaseService> services) {
        this.services = services;
    }
}
