dependencies {
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    implementation 'net.dv8tion:JDA:5.2.1'
    implementation "ch.qos.logback:logback-classic:1.5.6"
    implementation 'io.github.cdimascio:java-dotenv:5.2.2'

    // Ajouter les dépendances pour Jackson
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.2'
    implementation 'com.fasterxml.jackson.core:jackson-annotations:2.15.2'
    implementation 'com.fasterxml.jackson.core:jackson-core:2.15.2'
}