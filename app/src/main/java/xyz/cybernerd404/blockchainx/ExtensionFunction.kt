package xyz.cybernerd404.blockchainx

import java.security.MessageDigest

/*fun String.md5(): String {
    return hashString(this, "MD5")
}

fun String.sha256(): String {
    return hashString(this, "SHA-256")
}*/

fun hashString(input: BlockModel, algorithm: String): String {
    return MessageDigest
        .getInstance(algorithm)
        .digest("${input.hash}${input.message}".toByteArray())
        .fold("", { str, it -> str + "%02x".format(it) })
}