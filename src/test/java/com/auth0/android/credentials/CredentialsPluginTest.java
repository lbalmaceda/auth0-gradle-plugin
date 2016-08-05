package com.auth0.android.credentials;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CredentialsPluginTest {

    @Test
    public void shouldAddCredentialsTask() {
        Project project = ProjectBuilder.builder().build();
        project.getPluginManager().apply("com.auth0.android.credentials");

        assertTrue(project.getTasks().getByPath("generateAuth0Credentials") instanceof CredentialsTask);
    }

    @Test
    public void shouldAddCredentialsExtension() {
        Project project = ProjectBuilder.builder().build();
        project.getPluginManager().apply("com.auth0.android.credentials");

        assertTrue(project.getExtensions().getByName("auth0Credentials") instanceof CredentialsPluginExtension);
    }
}