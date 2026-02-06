class BaseConverter(val base: Int, val digits: IntArray) {

    init {
        if(base < 2) {
            throw IllegalArgumentException("Bases must be at least 2.")
        }
        if(digits.isEmpty()) {
            throw IllegalArgumentException("You must supply at least one digit.")
        }
        for(i in 0..digits.size - 1) {
            var digit = digits[i]
            if(digit >= base) {
                throw IllegalArgumentException("All digits must be strictly less than the base.")
            } else if(digit < 0) {
                throw IllegalArgumentException("Digits may not be negative.")
            } else if(i == 0 && digit == 0 && digits.size > 1) {
                throw IllegalArgumentException("Digits may not contain leading zeros.")
            }
        }
    }

    fun convertToBase(newBase: Int): IntArray {
        if(newBase < 2) {
            throw IllegalArgumentException("Bases must be at least 2.")
        }

        var base10 = toBase10()
        var toNewBase = fromBase10ToNewBase(base10, newBase)
        return toNewBase
    }


    fun toBase10(): Int {
        var numberOfDigits = digits.size
        var power = 1
        var valueInBase10 = 0
        for(i in 1..numberOfDigits) {
            valueInBase10 += digits[numberOfDigits - i] * power
            power *= base
        }
        return valueInBase10
    }

    fun fromBase10ToNewBase(value: Int, newBase: Int): IntArray {
        if(value == 0) {
            return intArrayOf(0)
        }
        var valueInBase10 = value
        var valueInNewBase = mutableListOf<Int>()
        while(valueInBase10 != 0) {
            var digit = valueInBase10 % newBase
            valueInNewBase.add(digit)
            valueInBase10 /= newBase
        }
        return valueInNewBase.reversed().toIntArray()
    }
}
