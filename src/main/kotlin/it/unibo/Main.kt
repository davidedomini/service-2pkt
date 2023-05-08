package it.unibo

import it.unibo.adapter.Service
import it.unibo.request.RequestParser
import it.unibo.tuprolog.core.Struct
import it.unibo.tuprolog.core.Var
import it.unibo.tuprolog.core.parsing.TermParser
import it.unibo.tuprolog.core.parsing.TermParserImpl
import it.unibo.tuprolog.solve.SolveOptions
import it.unibo.tuprolog.solve.classic.ClassicSolverFactory
import it.unibo.tuprolog.theory.parsing.ClausesParser

fun main(args: Array<String>) {
    Service().start()
}