@Grab('com.google.api-client:google-api-client:1.30.10')
@Grab('com.google.apis:google-api-services-containerregistry:v1-rev20210610-1.30.10')

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.containerregistry.ContainerRegistryScopes
import com.google.api.services.containerregistry.ContainerRegistry

def getGcrPaths(String projectId, String registry) {
    def credential = GoogleCredential.getApplicationDefault()
        .createScoped(ContainerRegistryScopes.all())
    def transport = GoogleNetHttpTransport.newTrustedTransport()
    def jsonFactory = JacksonFactory.getDefaultInstance()
    
    def registryService = new ContainerRegistry.Builder(transport, jsonFactory, credential)
        .setApplicationName('GCR-Paths-Script')
        .build()
    
    def paths = []
    
    def listRequest = registryService.projects().locations().repositories()
        .list("projects/$projectId/locations/*")
    
    def response
    do {
        response = listRequest.execute()
        response.repositories.each { repository ->
            def repoName = repository.name
            def listTagsRequest = registryService.projects().locations().repositories().tags()
                .list("projects/$projectId/locations/*", repoName)
            
            def tagsResponse = listTagsRequest.execute()
            tagsResponse.tags.each { tag ->
                paths << "gcr.io/$projectId/$repoName:$tag"
            }
        }
        
        listRequest.pageToken = response.nextPageToken
    } while (response.nextPageToken)
    
    return paths
}

def projectId = 'your-project-id'
def registry = 'your-registry'

def gcrPaths = getGcrPaths(projectId, registry)
gcrPaths.each { path ->
    println path
}
