# Using TFLite GPU on Android gives compile warning

Minimum reproducing sample:

1. Add tflite gpu dependency to build.gradle: `implementation("com.google.android.gms:play-services-tflite-gpu:16.2.0")`
2. Compile app: `./gradlew :app:assembleDebug`
3. Note the warning emitted: 

> [org.tensorflow:tensorflow-lite-api:2.12.0] ~/.gradle/caches/transforms-3/2eb840e608fe786447e3ad08d13f4541/transformed/tensorflow-lite-api-2.12.0/AndroidManifest.xml Warning:
> Namespace 'org.tensorflow.lite' is used in multiple modules and/or libraries: org.tensorflow:tensorflow-lite-api:2.12.0, org.tensorflow:tensorflow-lite-gpu-api:2.12.0. Please ensure that all modules and libraries have a unique namespace. For more information, See https://developer.android.com/studio/build/configure-app-module#set-namespace

Cause:

Using tflite-gpu adds both the -api and -gpu-api libraries (output from `./gradlew :app:dependencies`):

```
\--- com.google.android.gms:play-services-tflite-gpu:16.2.0
     +--- com.google.android.gms:play-services-base:18.1.0 (*)
     +--- com.google.android.gms:play-services-basement:18.1.0 (*)
     +--- com.google.android.gms:play-services-tasks:18.0.2 (*)
     +--- org.tensorflow:tensorflow-lite-api:2.12.0
     \--- org.tensorflow:tensorflow-lite-gpu-api:2.12.0
```

Both org.tensorflow:tensorflow-lite-api and org.tensorflow:tensorflow-lite-gpu-api specify the same namespace in their mainfest:

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="org.tensorflow.lite">
...
```
