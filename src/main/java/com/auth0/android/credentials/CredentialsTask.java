package com.auth0.android.credentials;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import groovy.transform.Field;

public class CredentialsTask extends DefaultTask {

    @TaskAction
    public void setCredentials() {
        CredentialsPluginExtension extension = getProject().getExtensions().findByType(CredentialsPluginExtension.class);
        if (extension == null) {
            throw new IllegalArgumentException("Error, extension was null");
        }

        String clientId = extension.getClientId();
        if (clientId == null || clientId.isEmpty()) {
            throw new IllegalArgumentException("ClientId must have a valid value!");
        }
        String domain = extension.getDomain();
        if (domain == null || domain.isEmpty()) {
            throw new IllegalArgumentException("Domain must have a valid value!");
        }

        System.out.println("ClientId is: " + clientId + " and Domain is: " + domain);
        createAuth0Resource(clientId, domain);
    }

    private void createAuth0Resource(String clientId, String domain) {
        File resValuesDir = new File(getProject().getBuildDir(), "generated/res/resValues/auth0");
        resValuesDir.mkdirs();
        File credentialsFile = new File(resValuesDir, "auth0_credentials.xml");
        if (credentialsFile.exists()) {
            credentialsFile.delete();
        }
        try {
            credentialsFile.createNewFile();

            PrintWriter writer = new PrintWriter(credentialsFile, "UTF-8");
            writer.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            writer.println("<resources>");
            writer.println("<string name=\"com_auth0_android_credentials_client_id\" translatable=\"false\">" + clientId + "</string>");
            writer.println("<string name=\"com_auth0_android_credentials_domain\" translatable=\"false\">" + domain + "</string>");
            writer.println("</resources>");
            writer.close();

            System.out.println("File created on: " + credentialsFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
