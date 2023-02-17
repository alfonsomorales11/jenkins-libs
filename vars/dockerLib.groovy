def build(Map params) {
    sh "scp -r * root@172.21.0.2:${params.build_path}"
    sh "ssh root@172.21.0.2 'cd ${params.build_path} && docker build -t ${params.repository_url}/${params.image_name}:${params.build_number} .'"
    sh "ssh root@172.21.0.2 'cd ${params.build_path} && rm -r *'"
}

def push(Map params) {
    sh "ssh root@172.21.0.2 'docker push ${params.repository_url}/${params.image_name}:${params.build_number}'"
}