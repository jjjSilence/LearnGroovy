import com.fasterxml.jackson.annotation.JsonSubTypes
import com.google.gson.Gson
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.xml.MarkupBuilder


//实体对象转化成json
def list = [new Person(name: 'Jhon', age: 1), new Person(name: 'Amy', age: 2)]
def json = JsonOutput.toJson(list)
println json

//json转xml
def slurper = new JsonSlurper()
List<Person> bean = slurper.parseText(json)
def sw = new StringWriter()
def xmlBuilder2 = new MarkupBuilder(sw)//用来生成xml的核心类
xmlBuilder2.data() {
    bean.each {
        person(name: it.name, age: it.age)
    }
}
println sw


//JsonOutput.prettyPrint(json)

//json字符串转化成实体对象
def jsonSlurper = new JsonSlurper()
List<Person> l = jsonSlurper.parseText(json)
println l[0].name

List<Person> l2 = jsonSlurper.parse(json.bytes)
println l2[0].name

def data = getNetworkData('https://suggest.taobao.com/sug?code=utf-8&q=1')
//println data.result.size()
println data.result[0]

def getNetworkData(String url) {
    def connection = new URL(url).openConnection()
    connection.setRequestMethod('GET')
    connection.connect()

    def response = connection.content.text
    println response
    def jsonSlurper = new JsonSlurper()
    return jsonSlurper.parseText(response)
}