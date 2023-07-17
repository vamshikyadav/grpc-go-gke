def getGcrPaths(String projectId, String registry) {
    def command = "gcloud container images list-tags --format='get(digest)' --project=$projectId --limit=UNLIMITED $registry"
    def process = command.execute()
    def output = process.text

    return output.readLines().collect { "gcr.io/$projectId/$registry@$it" }
}

def projectId = 'your-project-id'
def registry = 'your-registry'

def gcrPaths = getGcrPaths(projectId, registry)
gcrPaths.each { path ->
    println path
}
