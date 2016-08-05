package com.auth0.android.credentials;

import org.gradle.api.tasks.Input;

public class CredentialsPluginExtension {


    @Input
    private String clientId;
    @Input
    private String domain;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
