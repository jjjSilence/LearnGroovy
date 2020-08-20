import org.w3c.dom.ranges.Range

/**
 * 列表
 */
def findList = [1, 5, -2, 0, 9, 3, -11]
println findList.findAll {
    return it % 2 == 0
}
println findList.any {
    return it % 2 != 0
}
println findList.every {
    return it % 2 != 0
}
println findList.min {
    return Math.abs(it)
}
println findList.max {
    return Math.abs(it)
}
println findList.count {
    return it % 2 == 0
}


/**
 * 映射
 */
def map = [red: "ff0000", green: "00ff00", yellow: "0000ff", class: 1,]//不指定字符串默认编辑器默认指定单引号字符串
println map["red"]
println map.red
map.red = "fff0000"
println map.red

map.putAll([1: "a", 2: "b"])
println map
println map.getClass()
println map.class

//遍历
map.each {
    println it.key == 'red'
}
map.each { key, value ->
    println "The key is ${key}, the value is ${value}"
}
//带索引的遍历
map.eachWithIndex { Map.Entry<String, Serializable> entry, int i ->
    println "The index is ${i}, the key is ${entry.key}, the value is ${entry.value}"
}
map.eachWithIndex { key, value, int i ->
    println "The index is ${i}, the key is ${key}, the value is ${value}"
}

//collect：过滤
println map.findAll { return it.key.class == String }
        .collect {
            return it.value
        }

def groupMap = map.groupBy { it.key.class == String ? 'String' : "Other" }
println groupMap.toMapString()

//排序
println map.sort {
    it.value.class == Integer ? -1 : 1
}

/**
 * 范围查询
 */
def range = 1..10
println range[0]
println range.contains(9)
println range.from
println range.to
range.each {
    print it
}
println()

println getGrade(89)

def getGrade(int number) {
    String result
    switch (number) {
        case 0..59:
            result = '不及格'
            break
        case 60..70:
            result = '及格'
            break
        case 71..85:
            result = '良好'
            break
        case 86..100:
            result = '优秀'
    }
    return result
}

