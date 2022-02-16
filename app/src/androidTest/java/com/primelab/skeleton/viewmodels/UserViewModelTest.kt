package com.primelab.skeleton.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.primelab.common.session.UserSession
import com.primelab.common.session.UserToken
import com.primelab.skeleton.repository.UserRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 16/02/2022.
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class UserViewModelTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var rule: TestRule = InstantTaskExecutorRule()

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var userSession: UserSession

    private lateinit var userViewModel: UserViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        userViewModel = UserViewModel(userRepository)
    }

    @Test
    fun testUserToken() {
        val observer = Observer<UserToken?> {}
        try {
            userViewModel.repository.userSession.token.observeForever(observer)
            userViewModel.repository.userSession.setUserToken(UserToken("test_123"))
            val value = userViewModel.repository.userSession.token.value
            MatcherAssert.assertThat(value?.token, (not(nullValue())))

        } finally {
            userViewModel.repository.userSession.token.removeObserver(observer)
        }
    }

    @After
    fun tearDown() {
    }


    @Test
    fun loginUser() = runBlocking {
        val params = hashMapOf<String, Any>()
        val user = userViewModel.loginUser(params)
        assertNotNull(user.value)
        assertNotNull(user.asStateFlow().value)
        return@runBlocking
    }

    @Test
    fun getAllUser() = runBlocking {
        val user = userViewModel.getAllUser()
        assertNotNull(user.value)
        assertNotNull(user.asStateFlow().value)
        return@runBlocking
    }
}