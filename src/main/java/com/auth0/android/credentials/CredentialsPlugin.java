package com.auth0.android.credentials;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class CredentialsPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("auth0Credentials", CredentialsPluginExtension.class);
        project.getTasks().create("generateAuth0Credentials", CredentialsTask.class);
    }
}
