package de.richargh.sandbox.htmx.kotlinxhtml.commons.collections.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.math.max
import kotlin.math.min

class PagedCollectionTest {

    @ParameterizedTest
    @CsvSource(value = [
        "10:0:0:1",
        "10:0:1:1",
        "10:0:10:1",
        "10:10:11:2",
        "1:0:100:1",
        "1:1:100:2",
        "1:99:100:100",
        "10:0:100:1",
        "10:10:100:2",
        "10:50:100:6",
        "10:90:100:10",
        "50:0:1000:1",
        "50:50:1000:2",
        "50:450:1000:10",
        "50:950:1000:20"], delimiter = ':')
    fun `should calculate current page correctly`(limit: Int, offset: Int, total: Int, expectedPage: Int){
        // given
        val testee = PagedCollection<Int>(limit, offset, total, emptyList())
        // when
        val result = testee.currentPage
        // then
        assertEquals(expectedPage, result)
    }

    @ParameterizedTest
    @CsvSource(value = [
        "10:0:0:1",
        "10:0:1:1",
        "10:0:10:1",
        "10:10:11:2",
        "1:0:100:100",
        "1:1:100:100",
        "1:99:100:100",
        "10:0:100:10",
        "10:10:100:10",
        "10:50:100:10",
        "10:90:100:10",
        "50:0:1000:20",
        "50:50:1000:20",
        "50:450:1000:20",
        "50:950:1000:20"], delimiter = ':')
    fun `should calculate page count correctly`(limit: Int, offset: Int, total: Int, expectedPageCount: Int){
        // given
        val testee = PagedCollection<Int>(limit, offset, total, emptyList())
        // when
        val result = testee.pageCount
        // then
        assertEquals(expectedPageCount, result)
    }

}