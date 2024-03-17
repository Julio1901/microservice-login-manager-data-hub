package com.julio.loginmanager.models;

import jakarta.persistence.*;

import org.springframework.hateoas.RepresentationModel;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_LOGIN_MANAGER")
public class LoginModel extends RepresentationModel<LoginModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID loginId;
    private String serviceName;
    private String password;
    @Column(nullable = true)
    private String webSiteLink;
    @Column(nullable = true)
    private Integer payDay;
    private String owner;
    private String description;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getPayDay() {
        return payDay;
    }

    public void setPayDay(Integer payDay) {
        this.payDay = payDay;
    }

    public UUID getLoginId() {
        return loginId;
    }

    public void setLoginId(UUID loginId) {
        this.loginId = loginId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebSiteLink() {
        return webSiteLink;
    }

    public void setWebSiteLink(String webSiteLink) {
        this.webSiteLink = webSiteLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
