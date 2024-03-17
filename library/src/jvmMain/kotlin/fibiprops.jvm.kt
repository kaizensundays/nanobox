actual val firstElement: Int = 2
actual val secondElement: Int = 3

actual fun <C : CalculationContext> C.calculateOption(): Double {
    return Calculator().calculate()
}