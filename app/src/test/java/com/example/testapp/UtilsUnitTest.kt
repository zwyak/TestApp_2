package com.example.testapp

import com.example.testapp.model.Data
import com.example.testapp.utils.Util
import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilsUnitTest {
    @Test
    fun isSingleCase1() {
        val arr: Array<Data> = arrayOf(Data(1, "Belarus", 10))
        assertEquals(true, Util.isSingle(arr))
    }

    @Test
    fun isSingleCase2() {
        val arr: Array<Data> = arrayOf(Data(1, "Belarus", 10), Data(2, "Russia", 10))
        assertEquals(false, Util.isSingle(arr))
    }

}