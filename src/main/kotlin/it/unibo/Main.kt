package it.unibo

import it.unibo.tuprolog.core.Struct
import it.unibo.tuprolog.core.Var
import it.unibo.tuprolog.solve.classic.ClassicSolverFactory
import it.unibo.tuprolog.theory.parsing.ClausesParser

fun main(args: Array<String>) {
    println("Start")

    val t = with(ClausesParser.withStandardOperators()){
        parseTheory("f(davide).\nf(simone).")
    }

    println("clauses")
    t.clauses.forEach{ println(it) }
    println("directives")
    t.directives.forEach{ println(it) }

    val s = ClassicSolverFactory.solverOf(staticKb = t)
    val X = Var.of("X")
    s.solve(Struct.of("f", X)).forEach { println(it.substitution[X]) }

    println("End")
}