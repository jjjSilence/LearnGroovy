def a = 1.23
def result
switch (a.class) {
    case 1.23:
        result = 'find foo'
        break
    case 'bar':
        result = 'bar'
        break
    case [4, 5, 6, 'in list']:
        result = 'list'
        break
    case 12..30:
        result = 'range'
        break
    case Integer:
        result = "Integer"
        break
    case BigDecimal:
        result = "BigDecimal"
        break
    default:
        result = 'default'
}
println result

/**
 * 对数组进行循环
 */
def sum = 0
for (i in 0..9) {
    sum += i
}
println sum

/**
 * 对List进行循环
 */
sum = 0
for (i in [1, 2, 3, 4, 5, 6]) {
    sum += i
}
println sum

/**
 * 对Map进行循环
 */
sum = 0
for (i in ['a': 1, 'b': 2, 'c': 3]) {
    sum += i.value
}
println sum
