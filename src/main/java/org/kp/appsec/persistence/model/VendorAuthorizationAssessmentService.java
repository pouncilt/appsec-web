package org.kp.appsec.persistence.model;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Blob;

/**
 * Created by tonte on 7/1/15.
 */
public class VendorAuthorizationAssessmentService extends BaseService {
    @NotNull
    @Column(nullable = false)
    @Lob
    private Blob vendorAuthorizationForm;

    @Size(max = 500)
    private String vendorAuthorizationFormUrl;

    private VendorAuthorizationAssessmentService() {
        super();
    }

    public VendorAuthorizationAssessmentService(String name){
        super(name);
    }

    public VendorAuthorizationAssessmentService(String name, Blob vendorAuthorizationForm, String vendorAuthorizationFormUrl){
        super(name);
        this.vendorAuthorizationForm = vendorAuthorizationForm;
        this.vendorAuthorizationFormUrl = vendorAuthorizationFormUrl;
    }

    public Blob getVendorAuthorizationForm() {
        return vendorAuthorizationForm;
    }

    public void setVendorAuthorizationForm(Blob vendorAuthorizationForm) {
        this.vendorAuthorizationForm = vendorAuthorizationForm;
    }

    public String getVendorAuthorizationFormUrl() {
        return vendorAuthorizationFormUrl;
    }

    public void setVendorAuthorizationFormUrl(String vendorAuthorizationFormUrl) {
        this.vendorAuthorizationFormUrl = vendorAuthorizationFormUrl;
    }
}
