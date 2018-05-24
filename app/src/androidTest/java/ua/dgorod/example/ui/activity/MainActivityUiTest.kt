package ua.dgorod.example.ui.activity

import android.arch.lifecycle.MutableLiveData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import ua.dgorod.example.R
import ua.dgorod.example.model.PhotoUiModel
import ua.dgorod.example.viewmodel.MainViewModel
import ua.dgorod.example.viewmodel.Parcel

@RunWith(AndroidJUnit4::class)
class MainActivityUiTest: KoinTest {

    private val mockedViewModel = mock(MainViewModel::class.java)
    private val emptyData = MutableLiveData<Parcel<List<PhotoUiModel>>>()

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        `when`(mockedViewModel.photosList).thenReturn(emptyData)
    }

    //TODO: resolve how to do UI testing with Koin (context injection problem)
    //@Test
    fun mainActivityUiTest() {
        onView(withId(R.id.vEmptyText))
                .check(matches(isDisplayed()))
                .check(matches(withText(R.string.error_no_data)))
    }
}