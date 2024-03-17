actual val firstElement: Int = 3
actual val secondElement: Int = 5

actual fun <C : CalculationContext> C.calculateOption(): Double {
    return 0.37
}