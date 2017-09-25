package org.vld.sfpp.ch08

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PasswordValidatorTest {

    val validate: PasswordValidators =
            buildPasswordValidators(::isLongEnough, ::hasDigit, ::hasUppercase, ::hasLowercase, ::hasSymbol)

    @Test
    @DisplayName("Given an invalid password. When validate password. Then return non-empty list of errors")
    fun givenInvalidPassword_whenValidatePassword_thenReturnErrorList() {
        // Given
        val password = "_"
        // When
        val result = validate(password).filterNot(ValidationResult::isValid).map(ValidationResult::reason)
        // Then
        assertThat(result).hasSize(5)
    }

    @Test
    @DisplayName("Given a valid password. When validate password. Then return successful validation")
    fun givenValidPassword_whenValidatePassword_thenReturnSuccessfulValidation() {
        // Given
        val password = "abcDEF123!"
        // When
        val result = validate(password).filterNot(ValidationResult::isValid).map(ValidationResult::reason)
        // Then
        assertThat(result).isEmpty()
    }

}
