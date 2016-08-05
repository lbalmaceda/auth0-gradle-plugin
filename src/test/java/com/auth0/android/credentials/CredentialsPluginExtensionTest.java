package com.auth0.android.credentials;

import org.junit.Before;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CredentialsPluginExtensionTest {

    private CredentialsPluginExtension extension;

    @Before
    public void setUp() throws Exception {
        extension = new CredentialsPluginExtension("other-client", "other-domain");
    }

    @Test
    public void shouldSetClientId() throws Exception {
        extension.setClientId("my-value");
        assertThat(extension.clientId, is("my-value"));
    }

    @Test
    public void shouldGetClientId() throws Exception {
        extension.clientId = "my-value";
        assertThat(extension.getClientId(), is("my-value"));
    }

    @Test
    public void shouldSetDomain() throws Exception {
        extension.setDomain("my-value");
        assertThat(extension.domain, is("my-value"));
    }

    @Test
    public void shouldGetDomain() throws Exception {
        extension.domain = "my-value";
        assertThat(extension.getDomain(), is("my-value"));
    }

}