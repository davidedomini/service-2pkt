package it.unibo

import it.unibo.adapter.Service

fun main(args: Array<String>) {

    Service().start()

    /* Checks if isHalt
    val t = with(ClausesParser.withStandardOperators()){
        parseTheory("f :- f.")
    }

    val s = ClassicSolverFactory.solverOf(staticKb = t)

    println("First")
    s.solve(Struct.of("f."), 5).forEach { println("${it.isHalt}") }
    print(s.solveOnce(Struct.of("f")))
    println("Another one")
    //println(s.solveOnce(Struct.of("f", X)))*/

    //OK with limit
/*
    val t = with(ClausesParser.withStandardOperators()){
        parseTheory("f(1, davide).\nf(2, davide).")
    }
    val s = ClassicSolverFactory.solverOf(staticKb = t)
    val X = Var.of("X")
    println("First")
    s.solve(Struct.of("f", X), SolveOptions.someEagerly(1)).forEach { println(it) }
    println("Another one")

    println("End")*/
    
}