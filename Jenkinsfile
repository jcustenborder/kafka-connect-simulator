#!groovy
@Library('jenkins-pipeline') import com.github.jcustenborder.jenkins.pipeline.MavenUtilities

node {
    checkout scm

    docker.image(images.jdk8_docker_image).inside {
        def mvn = new MavenUtilities(steps)
        mvn.execute('clean package')
        junit '**/target/surefire-reports/TEST-*.xml'
    }
}
