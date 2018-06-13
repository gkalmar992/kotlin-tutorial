package gkalmar.kotlin.tutorial

fun main(args: Array<String>) {
    nullExamples()
}

fun nullExamples() {

//    var nullValue: String? = null
    fun nullFunction(): String? {return null}
    val nullVal = nullFunction()

    if (nullVal != null) {
        println("not null yaay")
    }

//    val check = nullVal!!.length // kotlin null pointer exception
//    println(check)

    //default value if null ELVIS OPERATOR ?:
    val initIfNull = nullVal ?: "Init before use pls."
    println(initIfNull)


}

interface ISheep {
    var isAlive: Boolean
    fun getInfo()
}

fun classExamples() {
    firstClassExample()
}

fun firstClassExample() {
    val sheep = Sheep("Test", 11, 11)
    sheep.health = 0
    sheep.getInfo()

    val superSheep = SuperSheep("Nero", 100, 1000)
    superSheep.getInfo()
}

open class Sheep(private val name: String,
                 var health: Int,
                 var dmg: Int,
                 override var isAlive: Boolean = true) : ISheep {
    init {
        require(health > 0 && health <= 100) { "min health = 1, max healt 100" }
    }

    open fun calculateAlive(): Boolean {
        if (isAlive) {
            isAlive = health > 0
        }
        return isAlive
    }

    override fun getInfo() {
        println("name: $name, health: $health, dmg: $dmg, isAlive: ${calculateAlive()}")
    }
}

open class SuperSheep(private val name: String,
                      health: Int, dmg: Int,
                      override var isAlive: Boolean = true) : Sheep(name, health, dmg, isAlive), ISheep {

    override fun getInfo() {
        println("Super sheep. name: $name, health: $health, dmg: $dmg, isAlive: ${calculateAlive()}")
    }

}

fun collectionExamples() {
    mapExamples()
    listMapExample()
    anyCollectionsExample()
    reduceListExample()
    modifyListExample()
    filterListExample()
    multiplyListExample()
}

fun otherExamples() {
    tailRecursiveExample("test")
    functionWhichIsBuildFunctionExample()
}

fun mapExamples() {
    val exampleMap = mutableMapOf("a" to 1, "b" to "2")

    exampleMap["a"] = 3
    exampleMap["b"] = 4

    for((key, value) in exampleMap) {
        println("$key $value; value class name: ${value.javaClass.name}")
    }
}


fun listMapExample() {
    val anyList = arrayOf("test", "test2", "something")
    val mappedList = anyList.map { "${it} edited" }
    mappedList.forEach(System.out::println)
}

fun anyCollectionsExample() {

    val anyList = arrayOf("test", "test2", "something")

    println("has any element ${anyList.any()}")
    println("has any element with test: ${anyList.any {element -> element.contains("test")}}")
    println("all element with test: ${anyList.all {element -> element.contains("test")}}")

    val onlyTests = anyList.filter { it.contains("test")}
    println("=== filtered list begins ===")
    onlyTests.forEach(System.out::println)

}

fun reduceListExample() {

    val numList = 1..10
    val sumOfTheList = numList.reduce{ x, y -> x + y }
    println(sumOfTheList)

    // fold adds plus value to the list
    val plusValue = 5
    val sumList = numList.fold(plusValue) { x, y -> x + y }
    println(sumList)
    println(sumOfTheList + plusValue == sumList )
}

fun modifyListExample() {
    val myNewFunction = {arg1: String -> "$arg1 modified"}
    val myList = mutableListOf("first", "second", "third")
    modifyMyList(myList, myNewFunction)
    myList.forEach(System.out::println)
}

fun modifyMyList(myList: MutableList<String>, myFunction: (arg1: String) -> String) {
    val tempList = mutableListOf<String>()
    for (myElement in myList) {
        tempList.add(myFunction(myElement))
    }
    myList.clear()
    myList.addAll(tempList)
}

fun functionWhichIsBuildFunctionExample() {
    val strConcat = functionWhichIsBuildFunction("ZSAkos")
    println("${strConcat("frodo")} walking at the park")
}

fun functionWhichIsBuildFunction(str: String): (String) -> String = {str2 -> "$str $str2"}

fun filterListExample() {
    val dummies = arrayOf("almafa", "kortefa", "ribizli", "bananfa", "barack")
    val filteredDummies = dummies.filter { it.contains("fa") }
    filteredDummies.forEach(System.out::println)
}

fun multiplyListExample() {
    val multiply = {num1: Int, num2: Int, num3 : Int -> num1 * num2 * num3}
    println("${multiply(2, 8, 4)}")
}

fun tailRecursiveExample(var1: String): String {
    var counter = 5
    tailrec fun saySomething(str: String): String {
        if (counter == 0) return "counter is 0"
        println("$str looped")
        counter --
        return saySomething(str)
    }

    return saySomething(var1)
}