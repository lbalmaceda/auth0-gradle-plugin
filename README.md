# Auth0 Gradle Plugin

This plugins set's the Auth0 credentials by specifying them on the `build.gradle` file. It creates a new a Task `generateAuth0Credentials` to generate the String resources in the corresponding flavor folder. If no flavor is detected, will default to `main`.

## Compile

1) Clone or download this project.
2) Run `./gradlew clean build install` so the plugin installs in the mavenLocal repository.

## Usage
1) Go to your project's `build.gradle` file and add the plugin dependency.

```groovy
buildscript {
    repositories {
        mavenLocal()
        //...
    }
    dependencies {
        classpath 'com.auth0.android:credentials-plugin:1.0.0-SNAPSHOT'
        //...
    }
}
```

2) Go to your module's `build.gradle` file and apply the plugin.

```groovy
apply plugin: 'com.auth0.android.credentials'

auth0Credentials {
    clientId = "MyClientId1234567890"
    domain = "domain.auth0.com"
}
```

3) Run the plugin

`./gradlew clean generateAuth0Credentials`