load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
load("@bazel_tools//tools/build_defs/repo:maven_rules.bzl", "maven_aar")

rules_kotlin_version = "87bd13f91d166a8070e9bbfbb0861f6f76435e7a" # How to check for lastest rules version?
http_archive(
    name = "io_bazel_rules_kotlin",
    urls = ["https://github.com/bazelbuild/rules_kotlin/archive/%s.zip" % rules_kotlin_version],
    type = "zip",
    strip_prefix = "rules_kotlin-%s" % rules_kotlin_version
)

load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kotlin_repositories", "kt_register_toolchains")
kotlin_repositories()
kt_register_toolchains()

# Google Maven Repository
GMAVEN_TAG = "20181212-2" # or the tag from the latest release
http_archive(
    name = "gmaven_rules",
    strip_prefix = "gmaven_rules-%s" % GMAVEN_TAG,
    url = "https://github.com/bazelbuild/gmaven_rules/archive/%s.tar.gz" % GMAVEN_TAG,
)

load("@gmaven_rules//:gmaven.bzl", "gmaven_rules")
gmaven_rules()

android_sdk_repository(name = "androidsdk")

maven_jar(
    name = "com_squareup_okhttp3_okhttp",
    artifact = "com.squareup.okhttp3:okhttp:3.12.0",
    sha1 = "b36f4a04584c0fb0d9af2d3401cdff8dacb1ea54",
)

maven_jar(
    name = "com_squareup_okio_okio",
    artifact = "com.squareup.okio:okio:2.1.0",
    sha1 = "5316c3393a9ff5c1b2af43df74463d63fb697a90",
)

maven_jar(
    name = "io_reactivex_rxjava2_rxjava2",
    artifact = "io.reactivex.rxjava2:rxjava:2.2.4",
    sha1 = "16ae5ef44181829f8f52caf61f131af9dfa3064a",
)

maven_jar(
    name = "org_reactivestreams_reactive_streams",
    artifact = "org.reactivestreams:reactive-streams:1.0.2",
    sha1 = "323964c36556eb0e6209f65c1cef72b53b461ab8",
)

maven_aar(
    name = "io_reactivex_rxjava2_rxandroid",
    artifact = "io.reactivex.rxjava2:rxandroid:2.1.0",
    sha1 = "1d05d7d192aa5f6989d352020bde5b8e2dfc45b2",
)