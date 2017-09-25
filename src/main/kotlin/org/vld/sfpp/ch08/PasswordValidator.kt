package org.vld.sfpp.ch08

/**
 * Validation result with the [isValid] indicator and the [reason] in case of invalid value
 */
open class ValidationResult(val isValid: Boolean, val reason: String?) {
    object IsValid : ValidationResult(true, null)
}

/**
 * Password validator accepts a password to validate and returns [ValidationResult]
 */
typealias PasswordValidator = (password: String) -> ValidationResult

/**
 * Composite password validator to validate password with multiple password validators
 */
typealias PasswordValidators = (password: String) -> List<ValidationResult>

/**
 * Validates if the [password] is long enough
 */
fun isLongEnough(password: String): ValidationResult =
        if (password.length >= 8) ValidationResult.IsValid
        else ValidationResult(false, "Password should have at least 8 characters")

/**
 * Validates if the [password] has digits
 */
fun hasDigit(password: String): ValidationResult =
        if (Regex("""\d""").containsMatchIn(password)) ValidationResult.IsValid
        else ValidationResult(false, "Password should have at least one digit")

/**
 * Validates if the [password] has uppercase letters
 */
fun hasUppercase(password: String): ValidationResult =
        if (Regex("""[A-Z]""").containsMatchIn(password)) ValidationResult.IsValid
        else ValidationResult(false, "Password should have at least one uppercase letter")

/**
 * Validates if the [password] has lowercase letters
 */
fun hasLowercase(password: String): ValidationResult =
        if (Regex("""[a-z]""").containsMatchIn(password)) ValidationResult.IsValid
        else ValidationResult(false, "Password should have at least one lowercase letter")

/**
 * Validates if the [password] has non alphanumeric values (symbols)
 */
fun hasSymbol(password: String): ValidationResult =
        if (Regex("""\W""").containsMatchIn(password)) ValidationResult.IsValid
        else ValidationResult(false, "Password should have at least one non alphanumeric symbol")

/**
 * Builds composite password validator from the provided [validators]
 */
fun buildPasswordValidators(vararg validators: PasswordValidator): PasswordValidators =
        { password: String -> validators.map { validate -> validate(password) } }
