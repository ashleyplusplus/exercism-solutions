object Luhn {

    fun isValid(candidate: String): Boolean {
        var candidateClean = candidate.filterNot { it.isWhitespace() }
        var numberLen = candidateClean.length
        if(numberLen == 1) {
            return false
        }
        var sum = 0;
        for(i in 1..numberLen) {
            var valueToSum = 0
            try {
                valueToSum = candidateClean[numberLen - i].digitToInt()
            } catch (e: IllegalArgumentException) {
                return false
            }
            if(i % 2 == 0) {
                valueToSum *= 2
                if(valueToSum > 9) {
                    valueToSum -= 9
                }
            }
            sum += valueToSum
        }

        if (sum % 10 == 0) {
            return true
        } else {
            return false
        }
    }
}
