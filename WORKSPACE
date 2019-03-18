load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

#Kotlin Rules
RULES_KOTLIN_VERSION = "da1232eda2ef90d4375e2d1677b32c7ddf09e8a1"
http_archive(
    name = "io_bazel_rules_kotlin",
    strip_prefix = "rules_kotlin-%s" % RULES_KOTLIN_VERSION,
    url = "https://github.com/bazelbuild/rules_kotlin/archive/%s.tar.gz" % RULES_KOTLIN_VERSION,
    sha256 = "0bbb0e5e536f0c775f37bded59d4f8cfb8556e6c3d926fcc0f58bf3489bff470",
)
load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kotlin_repositories", "kt_register_toolchains")
kotlin_repositories()
kt_register_toolchains()

RULES_JVM_EXTERNAL_TAG = "1.1"
RULES_JVM_EXTERNAL_SHA = "ade316ec98ba0769bb1189b345d9877de99dd1b1e82f5a649d6ccbcb8da51c1f"
http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

android_sdk_repository(
    name = "androidsdk",
    api_level = 28,
)

maven_install(
    artifacts = [
        "com.squareup.okhttp3:okhttp:3.14.0",
        "io.reactivex.rxjava2:rxjava:2.2.7",
        "io.reactivex.rxjava2:rxandroid:2.1.1",
        "androidx.core:core:1.0.1",
        "androidx.recyclerview:recyclerview:1.0.0",
        "org.reactivestreams:reactive-streams:1.0.2",
    ],
    repositories = [
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ],
)