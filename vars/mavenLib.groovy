def build(Map params) {
    sh "mvn ${params.steps}"
}