package org.kp.appsec.persistence.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by tonte on 7/1/15.
 */
@Embeddable
public class IdeInfo {
    @NotNull
    @Size(max = 25)
    @Column(nullable = false)
    private String name;
    @NotNull
    @Size(max = 20)
    @Column(nullable = false)
    private String version;

    private IdeInfo() {
        super();
    }

    public IdeInfo(String name, String version) {
        this.name = name;
        this.version = validateVersion(version);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = validateVersion(version);
    }

    private String validateVersion(String version) {
        String pattern = "^([0-9]+\\.?)+\\b";

        if(!version.matches(pattern)){
            throw new IllegalArgumentException("version can only float values i.e. 10.1.0");
        }

        return version;
    }

    @Override
    public int hashCode() {
        //int result = this.hashCode();
        int hash  = 5;
        hash = 89 * hash + ((name != null)? name.hashCode(): 0);
        hash = 89 * hash + ((version != null)? version.hashCode(): 0);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        IdeInfo other = (IdeInfo) obj;

        if(!name.equals(other.name)) return false;
        if(version != other.version) return false;

        return true;
    }
}
