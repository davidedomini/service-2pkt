package it.unibo.controller

import it.unibo.adapter.Service
import it.unibo.model.ComputationsCollection
import it.unibo.request.ComputationRequest
import it.unibo.tuprolog.core.parsing.TermParser
import it.unibo.tuprolog.solve.SolveOptions
import it.unibo.tuprolog.theory.parsing.ClausesParser
import it.unibo.tuprolog.solve.classic.ClassicSolverFactory

class Controller(
    private val service: Service
){

    private val computations = ComputationsCollection()

    fun solveAll(request: ComputationRequest) {
        val theory = with(ClausesParser.withDefaultOperators()){
            parseTheory(request.theory)
        }
        val goal = with(TermParser.withDefaultOperators()){
            parseStruct(request.goal)
        }
        val solver = ClassicSolverFactory.solverOf(staticKb = theory)
        val solutions =
            solver
                .solve(goal, SolveOptions.someEagerlyWithTimeout(request.maxSol, request.timeout))
                .take(request.maxSol)
                .map { it.substitution }
                .map { it.toString() }
                .reduce { acc, string -> acc + string }
        service.sendResponse(solutions)

    }

    fun solveNext(request: ComputationRequest){
        //TODO
    }

}