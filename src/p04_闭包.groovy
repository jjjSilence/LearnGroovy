//闭包定义
def courser = { println 'Hello Groovy' }
//闭包调用
courser.call()

//闭包的多个参数定义
def courser2 = { String name, int age -> println "Hello ${name}, my age is ${age} " }
courser2.call('Groovy', 10)

//闭包隐式的参数
def courser3 = { println "Hello ${it} " }
courser3.call('Groovy')

//闭包返回值
def courser4 = {
    return "Hello ${it} "
}
println courser4('Groovy')

/**
 * 求指定number的阶乘
 * @param number
 * @return
 */
int fab(int number) {
    int result = 1
    1.upto(number, { int num -> result *= num })
    return result
}

println fab(5)

int fab2(int number) {
    int result = 1
    number.downto(1) { int num -> result *= num }
    return result
}

println fab2(5)

int add(int number) {
    int result = 0;
    (++number).times {
        result += it
    }
    return result
}

println add(5)

/**
 * 闭包与String的结合使用
 */
String temp = "the 2 plus 3 is 5"

//each方法
println temp.each { print it.multiply(2) }

//find方法
println temp.find {
    it.isNumber()
}

//findAll方法
def list = temp.findAll {
    it.isNumber()
}
println list

//any方法
println temp.any {
    it.isNumber()
}

//every方法
print temp.every {
    it.isNumber()
}

//collect方法
list = temp.collect {
    it.toUpperCase()
}
println list

/**
 * 闭包的三个变量，this，owner,delegate
 */
def scriptClosure = {
    println this //闭包定义处的类
    println owner //代表闭包定义处的类或对象
    println delegate //任意对象，默认值与owner一致
}

scriptClosure.call()

//去除Person中的static
def person = new Person()
person.classClosure.call()
person.say()

//添加Person中的static
//Person.classClosure.call()
//Person.say()

def nestClosure = {
    def innerClosure = {
        println "innerClosure this：" + this //闭包定义处的类
        println "innerClosure this：" + owner //代表闭包定义处的类或对象
        println "innerClosure this：" + delegate //任意对象，默认值与owner一致
    }
    innerClosure.delegate = person //修改默认的delegate
    innerClosure.call()

    println "nestClosure this：" + this //闭包定义处的类
    println "nestClosure this：" + owner //代表闭包定义处的类或对象
    println "nestClosure this：" + delegate //任意对象，默认值与owner一致
}
nestClosure.call()

/**
 * 闭包委托策略
 */
class Student {
    String name
    def pretty = {
        "my Name is ${name}"
    }

    String toString() {
        pretty.call()
    }
}

class Teacher {
    String name
}

def student = new Student(name: "JJJ")
def teacher = new Teacher(name: "JJJ_Teacher")
student.pretty.delegate = teacher
student.pretty.resolveStrategy = Closure.DELEGATE_ONLY
println student.toString()

/**
 * 闭包和数据结构的结合
 */
//def list2 = new ArrayList() //java的定义方式
def list2 = [1, 2]
println list2.class
def array = [1, 2] as int[]
println array.class == int[]

//Java中自定义规则排序
def sortList1 = [1, 5, -2, 0, 9, 3, -9]
Comparator comparator = { a, b -> a == b ? 0 : Math.abs(a) < Math.abs(b) ? -1 : 1 }
Collections.sort(sortList1, comparator)
println sortList1

def sortList2 = [1, 5, -2, 0, 9, 3, -9]
sortList2.sort()
println sortList2

def sortList3 = [1, 5, -2, 0, 9, 3, -9]
sortList3.sort { a, b -> a == b ? 0 : Math.abs(a) < Math.abs(b) ? -1 : 1 }
println sortList3

//字符串排序
def sortStringList = ["abc", "z", "Hello", "groovy", "java"]
sortStringList.sort {
    return it.length()
}
println sortStringList
