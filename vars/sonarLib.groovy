def verify(Map params) {
    withCredentials([string(credentialsId: 'SONAR_TOKEN', variable: 'SONAR_TOKEN'), string(credentialsId: 'SONAR_TOKEN_DEV', variable: 'SONAR_TOKEN_DEV')]) {
        if (env.BRANCH_NAME == "development"){
            env.SONAR_TOKEN_X = "$SONAR_TOKEN_DEV"
        }

        if (env.BRANCH_NAME == "master"){
            env.SONAR_TOKEN_X = "$SONAR_TOKEN"
        }

        sh "mvn clean verify sonar:sonar \
            -Dsonar.projectKey=${params.project_key} \
            -Dsonar.host.url=${params.sonar_url} \
            -Dsonar.login=$SONAR_TOKEN_X"
    }
}