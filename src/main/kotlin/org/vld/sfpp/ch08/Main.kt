package org.vld.sfpp.ch08

fun main(args: Array<String>) {
    val validate = buildPasswordValidators(::isLongEnough, ::hasDigit, ::hasUppercase, ::hasLowercase, ::hasSymbol)
    val password = "_"
    val result = validate(password).filterNot(ValidationResult::isValid).map(ValidationResult::reason)
    result.forEach(::println)
}
