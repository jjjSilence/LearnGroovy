/**
 * 无论直接调用.还是直接用get/set，最终都走get/set方法
 */
def person = new Person(name: 'JJJ', age: 27)
println "The name is ${person.name}, the age is ${person.age}"

println person.cry("x")

//为类动态添加属性
Person.metaClass.sex = 'male'
def person2 = new Person(name: "JJJ", age: 27, sex: 'female')
println person2.sex

//为类动态添加方法
Person.metaClass.SexUpperCase = {
    -> sex.toUpperCase()
}
def person3 = new Person(name: "JJJ", age: 27, sex: 'female')
println person3.SexUpperCase()

//为类动态添加静态方法
Person.metaClass.static.createPerson = {
    String name, int age, String sex -> new Person(name: name, age: age, sex: sex)
}
def person4 = Person.createPerson('JJJ', 27, 'female')
println "The name is ${person4.name}, the age is ${person4.age}, the sex is ${person4.sex}"


/**
 * 一次注入，【动态添加静态方法】在应用程序其他模块都可使用
 */
Entry.main()

/**
 * 模拟入口类
 */
class Entry {
    static void main(def ages) {
        println '模拟应用程序启动'
        //初始化
        ApplicationManager.init()
        println '模拟应用初始化完成'

        def person = PersonManager.createPerson("JJJ", 22)
        println "The name is ${person.name}, the age is ${person.age}"

        println StringManager.reverseAndToUpperCase('abc')
    }
}

/**
 * 模拟一个应用管理类
 */
class ApplicationManager {
    static void init() {
        ExpandoMetaClass.enableGlobally()
        //为第三方类添加方法
        Person.metaClass.static.createPerson = {
            String name, int age -> new Person(name: name, age: age)
        }
        String.metaClass.static.reverseAndToUpperCase = {
            String s -> s.reverse().toUpperCase()
        }
    }
}

/**
 * Person的管理类
 */
class PersonManager {
    static Person createPerson(String name, int age) {
        return Person.createPerson(name, age)
    }
}

class StringManager {
    static String reverseAndToUpperCase(String s) {
        return String.reverseAndToUpperCase(s)
    }
}




