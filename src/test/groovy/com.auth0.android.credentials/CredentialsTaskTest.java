package com.auth0.android.credentials;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

import static org.testng.Assert.assertTrue;

public class CredentialsTaskTest {

    @Test
    public void shouldAddCredentialsTaskToTheProject() {
        Project project = ProjectBuilder.builder().build();
        final Task task = project.task("credentials");
        assertTrue(task instanceof CredentialsTask);
    }
}