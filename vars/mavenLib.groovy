def build(Map params) {
    sh "mvn ${params.commands}"
}