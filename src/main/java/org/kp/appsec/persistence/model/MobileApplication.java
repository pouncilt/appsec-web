package org.kp.appsec.persistence.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by tonte on 7/1/15.
 */
public class MobileApplication extends Application {
    @NotNull
    @Size(max = 500)
    @Column(nullable = false)
    private String downloadUrl;
    @NotNull
    @Size(max = 100)
    @Column(nullable = false)
    private String deviceType;

    public MobileApplication() {
        super();
        super.type = ApplicationType.MOBILE;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
