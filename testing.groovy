// files
def filePath = "path/to/your/file.txt"

new File(filePath).eachLine { line ->
    // Perform operations on each line here
    println(line)
}
