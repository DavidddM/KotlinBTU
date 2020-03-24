class Fraction(private var numerator: Int, private var denominator: Int) {

    override fun toString(): String {
        return "$numerator/$denominator"
    }

    private fun gcd(a: Int, b: Int): Int {
        if (b == 0)
            return a
        return gcd(b, a % b)
    }

    fun reduce() {
        val divisor = gcd(this.numerator, this.denominator)
        this.numerator /= divisor
        this.denominator /=  divisor
    }

    fun multiply(fraction: Fraction): Fraction {
        return Fraction(fraction.numerator * this.numerator,
            fraction.denominator * this.denominator)
    }

}

fun main() {
    val frac1 = Fraction(14,21)
    val frac2 = Fraction(12,26)

    print("Initial (Frac 1): $frac1\n")
    frac1.reduce()
    print("After Reduce (Frac1): $frac1\n")

    print("Initial (Frac 2): $frac2\n")
    frac2.reduce()
    print("After Reduce (Frac2): $frac2\n")

    val frac3 = frac1.multiply(frac2).reduce()

    print("$frac1 * $frac2 = $frac3")
}