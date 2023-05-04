package it.unibo.model

import it.unibo.tuprolog.solve.Solution

class ComputationsCollection {

    var computations: Map<String, Iterator<Solution>> = emptyMap()

    fun addComputation(id: String, c: Iterator<Solution>) {
        computations = computations + mapOf(id to c)
    }

    fun removeComputation(id: String) {
        computations = computations.filterNot { it.key == id }
    }

    fun nextSolution(id: String): Solution? {
        return computations[id]?.next()
    }

}