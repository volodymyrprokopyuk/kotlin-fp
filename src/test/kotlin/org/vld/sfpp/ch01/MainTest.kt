package org.vld.sfpp.ch01

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MainTest {

    @Test
    @DisplayName("First test display name")
    fun firstTest() {
        assertThat(2, equalTo(1 + 1))
    }

    @Test
    @Disabled
    fun secondTest() {
        assertThat(2, equalTo(1 + 1))
    }
}