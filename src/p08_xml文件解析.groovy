import groovy.xml.MarkupBuilder
import groovy.xml.XmlSlurper

final String xml = '''
<layout>
    <langs type='current' count='2'>
        <language flavor='static' version='1.5'>Java</language>
        <language flavor='dynamic' version='1.6'>Groovy</language>
    </langs>
    <langs2 type='current' count='2'>
        <language flavor='static' version='8.0'>Android</language>
        <language flavor='dynamic' version='1.6'>JavaScript</language>
    </langs2>
</layout>
'''

//开始解析xml数据
def xmlSlurper = new XmlSlurper()
def response = xmlSlurper.parseText(xml)
//对应结点的值
println response.langs.language[1]
println response.langs.language[0].text()

//对应结点的属性
println response.langs.language[0].@version

//结点遍历
response.langs.language.each {
    println it
}

String s = "aa"
println s.contains('b')

/**
 * 深度遍历
 */
def node = response.depthFirst().findAll {
    it.text() == 'Groovy'
}
println "深度遍历：" + node

/**
 * 广度遍历
 */
def node2 = response.langs.children().findAll {
    return true
}
println "广度遍历：" + node2

/**
 * 创建xml
 */
/**
 * <langs type='current' count ='2'>
 <language flavor='static' version='1.5'>Java</language>
 <language flavor='dynamic' version='1.6'>Groovy</language>
 </langs>
 */
/**
 * 静态手写xml数据
 */
def sw = new StringWriter()
def xmlBuilder = new MarkupBuilder(sw)//用来生成xml的核心类
//创建跟节点langs
xmlBuilder.langs(type: 'current', count: '2') {
    //第一个language结点
    language(flavor: 'static', version: '1.5', 'Java') {
    }
    language(flavor: 'dynamic', version: '1.6', 'Groovy') {
    }
}
println sw

//动态生成xml
def sw2 = new StringWriter()
def xmlBuilder2 = new MarkupBuilder(sw2)//用来生成xml的核心类
def langs = new Langs()
xmlBuilder2.langs(type: langs.type, count: langs.count) {
    langs.language.each {
        language(flavor: it.flavor, version: it.version, it.value)
    }
}
println sw2

class Langs {
    String type = 'current'
    int count = 2
    def language = [new Language(flavor: 'static', version: '1.5', value: 'Java'),
                    new Language(flavor: 'dynamic', version: '1.6', value: 'Groovy')]
}

class Language {
    String flavor;
    String version;
    String value;
}




