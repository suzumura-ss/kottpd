package com.github.gimlet2.kottpd

import java.io.BufferedReader
import java.io.InputStream

data class HttpRequest(val method: HttpMethod,
                       val url: String,
                       val httpVersion: String,
                       val headers: Map<String, String>,
                       val stream: BufferedReader,
                       val rawStream: InputStream) {
    val content: String by lazy {
        (1..headers.getOrElse("Content-Length", { "0" }).toInt()).fold("", { a, _ -> a + stream.read().toChar() })
    }
}