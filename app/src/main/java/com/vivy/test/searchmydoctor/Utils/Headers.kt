package com.vivy.test.searchmydoctor.Utils

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.vivy.test.searchmydoctor.Module.FetcherModule

internal object Headers {

    fun getUrlWithHeaders(url: String): GlideUrl {
        val authToken = FetcherModule.tokenRepository().getAccessToken()
        return GlideUrl(url, LazyHeaders.Builder()
                .addHeader("Authorization", "Bearer $authToken")
                .build())
    }
}