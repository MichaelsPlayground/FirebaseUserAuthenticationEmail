# Firebase User authentication with email and password

deutsche Anleitung: https://firebase.google.com/docs/auth/android/password-auth

```plaintext
dependencies {
    // Import the BoM for the Firebase platform
    implementation platform('com.google.firebase:firebase-bom:30.1.0')

    // Declare the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation 'com.google.firebase:firebase-auth'
}
```

LogIn to firebase console: https://console.firebase.google.com

UserAuthEmailPassword

ohne Google Analytics

Android app registrieren: de.androidcrypto.firebaseuserauthenticationemail

SHA1 Hash (Gradle signingReport):

SHA1: 19:22:A4:D7:01:A8:3D:09:8F:04:93:E9:8E:21:92:2D:5A:5F:B0:54

SHA-256: A7:A8:66:27:C7:76:6D:C3:3C:9E:3F:89:99:88:3E:A1:7B:ED:34:69:19:83:B6:EA:72:04:C9:13:8E:84:E0:90

Download der google-services.json Datei, Speicherung im app Verzeichnis

In Gradle modul:

apply plugin: 'com.google.gms.google-services'






