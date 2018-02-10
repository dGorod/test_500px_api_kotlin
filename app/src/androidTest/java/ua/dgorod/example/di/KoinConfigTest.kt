package ua.dgorod.example.di

import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.dryRun

/**
 * Test to check Koin dependency injection graph setup through initializing each bean.
 * Koin start and close procedures are covered by [KoinTest] class.
 *
 * @author Dmytro Gorodnytskyi
 * on 10-Feb-18.
 */
@RunWith(AndroidJUnit4::class)
class KoinConfigTest : KoinTest {

    @Test
    fun test(){
        dryRun()
    }
}
