@Grab('com.google.cloud:google-cloud-storage:1.128.1')

import com.google.cloud.storage.Blob
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions

def listPathsAndSubpathsInGCR(String projectId, String repositoryName) {
    def storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService()
    def bucketName = "artifacts.${projectId}.appspot.com"
    
    def blobs = storage.list(bucketName, Storage.BlobListOption.prefix(repositoryName)).iterateAll()
    
    def paths = []
    
    blobs.each { Blob blob ->
        def path = blob.getName()
        paths.add(path)
    }
    
    return paths
}

// Example usage
def projectId = "your-project-id"
def repositoryName = "your-repository-name"

def paths = listPathsAndSubpathsInGCR(projectId, repositoryName)

paths.each { path ->
    println(path)
}
