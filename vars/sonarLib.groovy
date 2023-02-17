def verify(Map params) {
    withCredentials([string(credentialsId: 'SONAR_TOKEN', variable: 'SONAR_TOKEN'), string(credentialsId: 'SONAR_TOKEN_DEV', variable: 'SONAR_TOKEN_DEV')]) {
        if (env.BRANCH_NAME == "development"){
            env.SONAR_TOKEN_X = "$SONAR_TOKEN_DEV"
            env.PROJECT_KEY_X = "$PROJECT_KEY_DEV"
        }

        if (env.BRANCH_NAME == "master"){
            env.SONAR_TOKEN_X = "$SONAR_TOKEN"
            env.PROJECT_KEY_X = "$PROJECT_KEY"
        }

        sh "mvn clean verify sonar:sonar \
            -Dsonar.projectKey=${project_key} \
            -Dsonar.host.url=${sonar_url} \
            -Dsonar.login=${sonar_token}"
    }
}