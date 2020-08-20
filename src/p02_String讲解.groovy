//一个单引号
def name = 'a single \'a\' String'
println name + "---" + name.class

//三个引号
def name2 = '''three single string'''
println name2 + "---" + name2.class
//三个引号保留数据格式
def name3 = '''\'a\'
line one
line two
line three'''
println name3 + "---" + name3.class

//双引号
def name4 = "this is a common String"
println name4 + "---" + name4.class
//双引号即可扩展字符
def name5 = "Qndroid"
def name6 = "Hello, ${name5}"
println name6 + "---" + name6.class

//GString即String互相转换
def name7 = echo(name6)
println name7 + "---" + name7.class

String echo(String message) {
    return message;
}

//从中间开始插入8个字符
def name8 = "name"
println name8.center(8, 'a')

//往左插入8个字符
def name9 = "name"
println name9.padLeft(8, 'a')

//判断字符串长度大小
println name8 >= name9

//获取第0个，第3位字符
println name8[0,3]

//减去第一个相同的字符串
println 'name name name' - 'na'

//倒叙
println 'name'.reverse()

//首字母大写
println 'name'.capitalize()

//字符串转其他类型
println '111'.toInteger()


