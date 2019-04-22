package app.utils

class DocumentValidation{
    private val cpfFirstDigitWeight = arrayListOf(10,9,8,7,6,5,4,3,2)
    private val cpfSecondDigitWeight = arrayListOf(11,10,9,8,7,6,5,4,3,2)

    private val cnhDigitWeight = arrayListOf(9,8,7,6,5,4,3,2,1)
    private val blacklist = arrayOf("00000000000", "11111111111", "22222222222", "33333333333", "44444444444",
            "55555555555", "66666666666", "77777777777", "88888888888", "99999999999")

    fun validateCpf(cpf: String) : Boolean{


        if(blacklist.contains(cpf)){
            return false
        }

        val digits = cpf.dropLast(2)
        val validationDigits = cpf.slice(9..10)

        val calcDigitOne = digitCalculation(digits, cpfFirstDigitWeight, 11, true)
        val calcDigitTwo = digitCalculation(digits.plus(calcDigitOne), cpfSecondDigitWeight, 11, true)

        val validation = "$calcDigitOne$calcDigitTwo"

        if(validation != validationDigits){
            return false
        }
        return true
    }

    fun validateChn(cnh: String) : Boolean{
        val digits = cnh.dropLast(2)
        val validationDigits = cnh.slice(9..10)
        var descDig2 = 0

        if(blacklist.contains(cnh)) {
            return false
        }

        var calcDigitOne = digitCalculation(digits, cnhDigitWeight, 11, false)
        if(calcDigitOne >= 10){
            descDig2 = 2
            calcDigitOne = 0
        }
        var calcDigitTwo = digitCalculation(digits, cnhDigitWeight.reversed(), 11, false)
        if (calcDigitTwo >= 10){
            calcDigitTwo = 0
        }
        else{
            calcDigitTwo - descDig2
        }

        val validation = "$calcDigitOne$calcDigitTwo"

        if(validation != validationDigits){
            return false
        }
        return true
    }

    // BaseMinus signals what type of return will be, either base minus mod of sum or mod of sum.

    private fun digitCalculation(digits: String, weightMash: List<Int>, base: Int, baseMinus: Boolean) : Int{
        var total = 0
        for (i in 0 until digits.length){
            total += (digits[i].toString().toInt() * weightMash[i])
        }
        return if(baseMinus) {
            base - (total % base)
        }
        else{
            total % base
        }
    }
}