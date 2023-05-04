package it.unibo

import it.unibo.tuprolog.core.Struct
import it.unibo.tuprolog.core.Var
import it.unibo.tuprolog.solve.classic.ClassicSolverFactory
import it.unibo.tuprolog.theory.parsing.ClausesParser


class Person(val name: String)

fun main(args: Array<String>) {

    val d = Person("Davide")

    println(d.name)
    /*println("Start")

    val t = with(ClausesParser.withStandardOperators()){
        parseTheory("f :- f.")
    }

    println("clauses")
    t.clauses.forEach{ println(it) }
    println("directives")
    t.directives.forEach{ println(it) }

    val s = ClassicSolverFactory.solverOf(staticKb = t)
    //val X = Var.of("X")


    println("First")
    //s.solve(Struct.of("f."), 5).forEach { println("ciao ${it.isHalt}") }
    print(s.solveOnce(Struct.of("f")))
    println("Another one")
    //println(s.solveOnce(Struct.of("f", X)))

    println("End")*/
    
}