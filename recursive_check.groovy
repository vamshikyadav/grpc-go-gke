ef getAllPaths(File directory) {
    def paths = []
    directory.eachFile { file ->
        if (file.isDirectory()) {
            paths << getAllPaths(file) // Recursively call the function for subdirectories
        } else {
            paths << file.getAbsolutePath() // Add the path to the list
        }
    }
    return paths.flatten()
}

def folderPath = 'path/to/your/folder' // Replace 'path/to/your/folder' with the actual folder path
def folder = new File(folderPath)

if (folder.isDirectory()) {
    def allPaths = getAllPaths(folder)
    allPaths.each { path ->
        println path // Print each path
    }
} else {
    println "Invalid folder path: $folderPath"
}
In 
