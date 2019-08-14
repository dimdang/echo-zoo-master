package org.cool.zoo.entities.token;

import javax.persistence.*;

@Table(name = "oauth_client_details")
@Entity
public class OauthClientDetail {

    private Long id;
    private String clientId;
    private String resourceId;
    private String clientSecret;
    private String scope;
    private String authGrantType;
    private String urlDirection;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokeValidity;
    private String additionalInformation;
    private String autoApprove;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "CLIENT_ID")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Column(name = "RESOURCE_IDS")
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Column(name = "CLIENT_SECRET")
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Column(name = "SCOPE")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Column(name = "AUTHORIZED_GRANT_TYPES")
    public String getAuthGrantType() {
        return authGrantType;
    }

    public void setAuthGrantType(String authGrantType) {
        this.authGrantType = authGrantType;
    }

    @Column(name = "WEB_SERVER_REDIRECT_URI")
    public String getUrlDirection() {
        return urlDirection;
    }

    public void setUrlDirection(String urlDirection) {
        this.urlDirection = urlDirection;
    }

    @Column(name = "AUTHORITIES")
    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @Column(name = "ACCESS_TOKEN_VALIDITY")
    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    @Column(name = "REFRESH_TOKEN_VALIDITY")
    public Integer getRefreshTokeValidity() {
        return refreshTokeValidity;
    }

    public void setRefreshTokeValidity(Integer refreshTokeValidity) {
        this.refreshTokeValidity = refreshTokeValidity;
    }

    @Column(name = "ADDITIONAL_INFORMATION")
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Column(name = "AUTOAPPROVE")
    public String getAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(String autoApprove) {
        this.autoApprove = autoApprove;
    }
}
