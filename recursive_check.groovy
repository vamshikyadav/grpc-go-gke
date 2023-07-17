def getGcrPaths(String projectId, String registry) {
    def command = "gcloud container images list-tags --format='get(digest)' --limit=999999 --filter='*/*' --registry=${registry} --project=${projectId}"
    def process = command.execute()
    def paths = process.text.readLines()

    paths.collect { path ->
        "gcr.io/${projectId}/${path}"
    }
}

def projectId = 'your-project-id'
def registry = 'your-registry'

def gcrPaths = getGcrPaths(projectId, registry)
gcrPaths.each { path ->
    println path
}
