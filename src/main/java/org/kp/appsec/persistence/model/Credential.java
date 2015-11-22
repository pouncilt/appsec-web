package org.kp.appsec.persistence.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by tonte on 7/1/15.
 */
@Embeddable
public class Credential {
    @NotNull
    @Size(max = 25)
    @Column(nullable = false)
    private String username;
    @NotNull
    @Size(max = 25)
    @Column(nullable = false)
    private String password;
    @NotNull
    @Size(max = 50)
    @Column(nullable = false)
    private String appEnvironment;
    @Size(max = 500)
    private String url;
    @NotNull
    @Size(max = 25)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CredentialType credentialType; // source code, application, continuous assessment

    private Credential() {
        super();
    }

    public Credential(String username, String password, String appEnvironment, String url, CredentialType credentialType) {
        this.username = username;
        this.password = password;
        this.appEnvironment = appEnvironment;
        this.url = url;
        this.credentialType = credentialType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppEnvironment() {
        return appEnvironment;
    }

    public void setAppEnvironment(String appEnvironment) {
        this.appEnvironment = appEnvironment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CredentialType getCredentialType() {
        return credentialType;
    }

    public void setCredentialType(CredentialType credentialType) {
        this.credentialType = credentialType;
    }
}
