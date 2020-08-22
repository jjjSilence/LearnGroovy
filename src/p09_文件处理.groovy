/**
 * 读取整个文件
 */
def file = new File('Person.groovy')
//file.eachLine {
//    println it
//}

//println file.getText()

println file.readLines()

/**
 * 读取文件部分内容
 */
def reader = file.withReader {
    char[] buffer = new char[100]
    it.read(buffer)
    return buffer
}
println reader

/**
 * 写内容去文件中
 */
//def writer = file.withWriter{
//    it.write("//test")
//}
//copy('Person.groovy', 'CopyPerson.groovy')

def copy(String sourcePath, String targetPath) {
    try {
        //创建目标文件
        def targetFile = new File(targetPath)
        if (!targetFile.exists()) {
            targetFile.createNewFile()
        }
        //开始copy
        //copy方式一
        new File(sourcePath).withReader {
            def lines = it.readLines()
            targetFile.withWriter { writer ->
                lines.each { line ->
                    writer.append(line)
                    writer.append('\r\n')
                }
            }
        }
        //copy方式二
        // targetFile.withWriter { writer ->
        //            writer.write(new File(sourcePath).getText())
        //        }
        return true
    } catch (Exception e) {
        e.printStackTrace()
    }
    return false
}

def person = new Person(name: "JJJ", age: 22)
saveObject(person, 'person.bin')
def result = readObject('person.bin')
println "The name is ${result.name}, the age is ${result.age}"
/**
 * 写对象到文件中
 * @param object
 * @param path
 * @return
 */
def saveObject(Object object, String path) {
    try {
        def file = new File(path)
        if (!file.exists()) {
            file.createNewFile()
        }
        file.withObjectOutputStream { out ->
            out.writeObject(object)
        }
        return true
    } catch (Exception e) {
        e.printStackTrace()
    }
    return false
}

def readObject(String path) {
    def object = null
    try {
        def file = new File(path)
        if (file == null || !file.exists()) {
            return object
        }
        file.withObjectInputStream { input ->
            object = input.readObject()
        }
    } catch (Exception e) {
        e.printStackTrace()
    }
    return object
}



