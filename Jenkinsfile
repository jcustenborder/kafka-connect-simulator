#!groovy
@Library('jenkins-pipeline') import com.github.jcustenborder.jenkins.pipeline.MavenUtilities

node {
    checkout scm

    stage('build') {
        docker.image(images.jdk8_docker_image).inside {
            configFileProvider([configFile(fileId: 'mavenSettings', variable: 'MAVEN_SETTINGS')]) {
                def mvn = new MavenUtilities(steps, "$MAVEN_SETTINGS")
                mvn.execute('clean package')
            }
            junit '**/target/surefire-reports/TEST-*.xml'
        }
    }
}
