a = 3.56
println(a)

b = "Hello gradle"
println(b)

def x = 11
def y = 3.1415
def name = "Groovy"
println(x + "--> " + x.class)
println(y + "--> " + y.class)
println(name + "--> " + name.class)

// 打开[p01_基础语法.class]文件，可以看到：使用def定义对象是Object类型，使用强类型定义就是具体的类型。
x = "Android"
println(x + "--> " + x.class)