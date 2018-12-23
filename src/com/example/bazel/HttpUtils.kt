/*
 * Copyright (C) 2016 Jonathan Steele
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.bazel

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import okhttp3.*
import java.io.InputStream
import java.io.IOException

object HttpUtils {
    private val client = OkHttpClient.Builder().build()

    fun parse(url: String): Flowable<InputStream> =
        Flowable.create<InputStream>({
            // Create request for remote resource.
            val request = Request.Builder().url(url).build()
            // Execute the request and retrieve the response.
            val newCall = client.newCall(request)
            newCall.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    // Don't bother with resuming the continuation if it is already cancelled.
                    if (call.isCanceled) return
                    it.onError(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful.not()) {
                        it.onError(IOException("Unexpected code " + response))
                        return
                    }
                    response.body()?.use { responseBody ->
                        it.onNext(responseBody.byteStream())
                        it.onComplete()
                    }
                }
            })
            it.setDisposable(object : Disposable {
                override fun dispose() = newCall.cancel()
                override fun isDisposed(): Boolean = newCall.isCanceled
            })
        }, BackpressureStrategy.LATEST)
}
