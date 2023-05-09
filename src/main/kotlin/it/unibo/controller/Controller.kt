package it.unibo.controller

import it.unibo.adapter.Service
import it.unibo.model.ComputationsCollection
import it.unibo.request.ComputationRequest
import it.unibo.tuprolog.core.Struct
import it.unibo.tuprolog.core.parsing.TermParser
import it.unibo.tuprolog.solve.SolveOptions
import it.unibo.tuprolog.solve.Solver
import it.unibo.tuprolog.theory.parsing.ClausesParser
import it.unibo.tuprolog.solve.classic.ClassicSolverFactory

class Controller(
    private val service: Service
){

    private val computations = ComputationsCollection()

    fun solveAll(request: ComputationRequest) {
        val p = getSolverAndGoal(request)
        val solutions =
            p.first
                .solve(p.second, SolveOptions.someEagerlyWithTimeout(request.maxSol, request.timeout))
                .take(request.maxSol)
                .map { it.substitution }
                .map { it.toString()}
                .reduce { acc, string -> acc + string }
        service.sendResponse(solutions)
    }

    fun solveNext(request: ComputationRequest){
        if(!computations.isPresent(request.id)) {
            val p = getSolverAndGoal(request)
            val iterator =
                p.first
                    .solve(p.second, SolveOptions.someEagerlyWithTimeout(request.maxSol, request.timeout))
                    .take(request.maxSol)
                    .iterator()
            computations.addComputation(request.id, iterator)
        }
        val solution = computations
            .nextSolution(request.id)
        when(solution){
            null -> {
                computations.removeComputation(request.id)
                service.sendResponse("No more solutions!")
            }
            else -> service.sendResponse(solution
                .substitution
                .toString())
        }
    }

    fun reset(request: ComputationRequest) {
        computations.removeComputation(request.id)
    }

    private fun getSolverAndGoal(request: ComputationRequest): Pair<Solver, Struct> {
        val theory = with(ClausesParser.withDefaultOperators()){
            parseTheory(request.theory)
        }
        val goal = with(TermParser.withDefaultOperators()){
            parseStruct(request.goal)
        }
        val solver = ClassicSolverFactory.solverOf(staticKb = theory)
        return Pair(solver, goal)
    }

}