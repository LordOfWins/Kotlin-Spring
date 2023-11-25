package org.springkotlin

fun main() {
    for (i in 1..5) {
        println("i is $i ")
        if (i == 3) break
    }
    println("End of program")

    label()
}

fun label() {
    loop@ for (i in 1..5) {
        println("i in label $i: ")
        innerLoop@ for (j in 1..10) {
            println("j in label $j: ")
            if (j == 2) break@innerLoop // this is just equivalent to the continue
            //  if(j==2) break@loop
        }
    }
}