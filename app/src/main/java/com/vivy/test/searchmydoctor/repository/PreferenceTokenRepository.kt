package com.vivy.test.searchmydoctor.repository

import com.vivy.test.searchmydoctor.Utils.StringEncryptor
import com.vivy.test.searchmydoctor.model.LoginToken

class PreferenceTokenRepository(private var stringEncryptor:
                                StringEncryptor, private var preferenceRepo: PreferenceRepo)
    : TokenRepository {

    override fun getAccessToken(): String {
        return stringEncryptor.encryptString(preferenceRepo.readPreference(preferenceRepo.PREF_TOKEN_FILE,
                preferenceRepo.PREF_ACCESS_TOKEN, ""))
    }

    override fun setAccessToken(accessToken: LoginToken) {
        preferenceRepo.writePreference(preferenceRepo.PREF_TOKEN_FILE, preferenceRepo.PREF_ACCESS_TOKEN,
                stringEncryptor.encryptString(accessToken.accessToken))
    }

    override fun getRefreshToken(): String {
        return stringEncryptor.encryptString(preferenceRepo.readPreference(preferenceRepo.PREF_TOKEN_FILE,
                preferenceRepo.PREF_REFRESH_TOKEN, ""))
    }

    override fun setRefreshToken(refToken: LoginToken) {
        preferenceRepo.writePreference(preferenceRepo.PREF_TOKEN_FILE, preferenceRepo.PREF_REFRESH_TOKEN,
                stringEncryptor.encryptString(refToken.refreshToken))
    }
}