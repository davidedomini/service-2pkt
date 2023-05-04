package it.unibo.controller

import it.unibo.model.ComputationsCollection
import it.unibo.request.ComputationRequest
import it.unibo.tuprolog.core.Struct
import it.unibo.tuprolog.solve.Solution
import it.unibo.tuprolog.theory.parsing.ClausesParser
import it.unibo.tuprolog.solve.classic.ClassicSolverFactory

class Controller {

    private val computations: ComputationsCollection = ComputationsCollection()

    fun solveAll(request: ComputationRequest): Sequence<Solution> { //TODO - return a better type
        val theory = with(ClausesParser.withStandardOperators()){
            parseTheory(request.theory)
        }
        val solver = ClassicSolverFactory.solverOf(staticKb = theory)
        return solver.solve(Struct.of(request.goal), 15)// TODO - parse the goal
    }

    fun solveOne(request: ComputationRequest){

    }

}