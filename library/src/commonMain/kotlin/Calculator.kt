class Calculator {

    fun calculate(): Double {
        return 0.37
    }

}

expect fun <C : CalculationContext> C.calculateOption(): Double