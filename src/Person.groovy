public class Person implements DefaultAction, Serializable {
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
