package com.primelab.skeleton.db

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.primelab.common.session.UserSession
import com.primelab.common.session.UserToken
import com.primelab.skeleton.database.AppDataBase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by m.imran
 * Senior Software Engineer at
 * PrimeLab.io on 10/02/2022.
 */
@RunWith(MockitoJUnitRunner::class)
class SessionTokenTest {
    private lateinit var appDB: AppDataBase
    private lateinit var userSession: UserSession
    private lateinit var context: Context

    companion object {
        private const val TOKEN = "test_token"
    }

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        appDB = AppDataBase.getInstance(context)
        userSession = UserSession(appDB.getUserTokenDao())
    }

    @Test
    fun tesUserToken() {
        userSession.token = UserToken(TOKEN)
        assertEquals(userSession.token?.token, TOKEN)
    }
}