node {
   stage('Clone') {
      checkout scm
   }

   stage('Build') {
       sh "mvn clean package -U"
   }

   stage('Archive') {
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
   }

   stage('Uploading to Nexus') {
      pom = readMavenPom file: 'pom.xml'
      nexusPublisher nexusInstanceId: 'ryannexus', nexusRepositoryId: 'peko', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: "target/${pom.name}.jar"]], mavenCoordinate: [artifactId: pom.artifactId, groupId: pom.groupId, packaging: 'jar', version: pom.version]]]
   }
}
