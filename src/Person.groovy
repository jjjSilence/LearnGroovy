public class Person implements DefaultAction {
    String name
    int age

    def increaseAge(int age) {
        this.age += age;
    }

    @Override
    void eat() {
    }

    /**
     * 一个方法找不到，调用它代替
     * @param name
     * @param args
     * @return
     */
    def invokeMethod(String name, Object args) {
        return "invokeMethod：the method is ${name}, the params is ${args}"
    }

    def methodMissing(String name, Object args) {
        return "methodMissing：the method is ${name}, the params is ${args}"
    }

    def classClosure = {
        println "classClosure this：" + this //闭包定义处的类
        println "classClosure owner：" + owner //代表闭包定义处的类或对象
        println "classClosure delegate：" + delegate //任意对象，默认值与owner一致
    }

    def say() {
        def methodClosure = {
            println "methodClosure this：" + this //闭包定义处的类
            println "methodClosure this：" + owner //代表闭包定义处的类或对象
            println "methodClosure this：" + delegate //任意对象，默认值与owner一致
        }
        methodClosure.call()
    }
}

/**
 * 接口中不允许非public类型的方法
 */
interface Action {
    void eat()

    void drink()

    void play()
}

trait DefaultAction {
    abstract void eat()

    void play() {
        println "I can play"
    }
}
