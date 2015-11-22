package org.kp.appsec.persistence.model;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Blob;
import java.util.Set;

/**
 * Created by tonte on 7/1/15.
 */
public class WAFService extends BaseService {
    @Valid
    @ElementCollection
    @CollectionTable(name="LoadBalancerIpAddresses", joinColumns = @JoinColumn(name="APP_ID"))
    @Column(name="IP_Address", nullable = false)
    private Set<String> loadBalancerIpAddresses;

    @Size(max = 25)
    @Enumerated(EnumType.STRING)
    private HttpProtocol httpProtocol;
    @NotNull
    @Column(nullable = false)
    @Lob
    private Blob productionDomainPublicCertificate;
    @NotNull
    @Column(nullable = false)
    @Lob
    private Blob productionDomainPrivateKey;
    @NotNull
    @Column(nullable = false)
    @Size(max = 100)
    private String dataCenter;

    private WAFService() {
        super();
    }

    public WAFService(String name) {
        super(name);
    }

    public WAFService(String name, Set<String> loadBalancerIpAddresses, HttpProtocol httpProtocol,
                      Blob productionDomainPublicCertificate, Blob productionDomainPrivateKey, String dataCenter) {
        super(name);
        this.loadBalancerIpAddresses = loadBalancerIpAddresses;
        this.httpProtocol = httpProtocol;
        this.productionDomainPublicCertificate = productionDomainPublicCertificate;
        this.productionDomainPrivateKey = productionDomainPrivateKey;
        this.dataCenter = dataCenter;
    }

    public Set<String> getLoadBalancerIpAddresses() {
        return loadBalancerIpAddresses;
    }

    public void setLoadBalancerIpAddresses(Set<String> loadBalancerIpAddresses) {
        this.loadBalancerIpAddresses = loadBalancerIpAddresses;
    }

    public HttpProtocol getHttpProtocol() {
        return httpProtocol;
    }

    public void setHttpProtocol(HttpProtocol httpProtocol) {
        this.httpProtocol = httpProtocol;
    }

    public Blob getProductionDomainPublicCertificate() {
        return productionDomainPublicCertificate;
    }

    public void setProductionDomainPublicCertificate(Blob productionDomainPublicCertificate) {
        this.productionDomainPublicCertificate = productionDomainPublicCertificate;
    }

    public Blob getProductionDomainPrivateKey() {
        return productionDomainPrivateKey;
    }

    public void setProductionDomainPrivateKey(Blob productionDomainPrivateKey) {
        this.productionDomainPrivateKey = productionDomainPrivateKey;
    }

    public String getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(String dataCenter) {
        this.dataCenter = dataCenter;
    }
}
