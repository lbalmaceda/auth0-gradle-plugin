package com.auth0.android.credentials;

import org.gradle.api.DefaultTask;
import org.gradle.api.Plugin;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CredentialsTask extends DefaultTask {

    private String clientId;
    private String domain;

    @TaskAction
    public void setCredentials() {
        CredentialsPluginExtension extension = getProject().getExtensions().findByType(CredentialsPluginExtension.class);
        if (extension == null) {
            throw new IllegalArgumentException("Error, extension was null");
        }

        parseAuth0Credentials(extension);

        System.out.println("ClientId is: " + clientId + " and Domain is: " + domain);
        for (String flavor : getFlavors()) {
            createAuth0Resource(clientId, domain, flavor);
        }
    }

    private void parseAuth0Credentials(CredentialsPluginExtension extension) {
        clientId = extension.getClientId();
        if (clientId == null || clientId.isEmpty()) {
            throw new IllegalArgumentException("ClientId must have a valid value!");
        }
        domain = extension.getDomain();
        if (domain == null || domain.isEmpty()) {
            throw new IllegalArgumentException("Domain must have a valid value!");
        }
    }

    private void createAuth0Resource(String clientId, String domain, String flavor) {
        File resValuesDir = new File(getProject().getBuildDir(), "generated/res/resValues/" + flavor);
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
            writer.println("<string name=\"com_auth0_android_client_id\" translatable=\"false\">" + clientId + "</string>");
            writer.println("<string name=\"com_auth0_android_domain\" translatable=\"false\">" + domain + "</string>");
            writer.println("</resources>");
            writer.close();

            System.out.println("File created on: " + credentialsFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchAndroidVariants() {
        boolean hasAndroidPlugin = getProject().getPlugins().hasPlugin("android");
        final Plugin android = getProject().getPlugins().getAt("android");
        if (!hasAndroidPlugin) {
            throw new IllegalArgumentException("You need to apply this plugin after you apply the 'android' one!");
        }
        System.out.println("Android plugin found.");

        logCollection("Tasks", getProject().getAllTasks(false).entrySet());
        System.out.println("Android plugin found.");

    }

    private Set<String> getFlavors() {
        String tskReqStr = getProject().getGradle().getStartParameter().getTaskRequests().toString();
        System.out.println("Using line: " + tskReqStr);
        Pattern pattern;
        if (tskReqStr.contains("assemble")) {
            pattern = Pattern.compile("assemble(\\w+)(Release|Debug)");
        } else {
            pattern = Pattern.compile("generate(\\w+)(Release|Debug)");
        }

        Matcher matcher = pattern.matcher(tskReqStr);

        Set<String> set = new HashSet<>();
        while (matcher.find()) {
            set.add(matcher.group(1).toLowerCase());
        }
        if (set.isEmpty()) {
            set.add("main");
        }
        return set;
    }

    private void logCollection(String desc, Collection c) {
        System.out.println("Now logging " + desc);
        for (Object o : c) {
            System.out.println(o);
        }

    }
}
