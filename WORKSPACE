load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
load("@bazel_tools//tools/build_defs/repo:maven_rules.bzl", "maven_aar")

RULES_KOTLIN_VERSION = "cab5eaffc2012dfe46260c03d6419c0d2fa10be0" # How to check for latest rules version?
http_archive(
    name = "io_bazel_rules_kotlin",
    urls = ["https://github.com/bazelbuild/rules_kotlin/archive/%s.zip" % RULES_KOTLIN_VERSION],
    type = "zip",
    strip_prefix = "rules_kotlin-%s" % RULES_KOTLIN_VERSION
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
    sha256 = "33027de68db6a49a352f83808fa9898c4930d39aa6fb0edc6bb3d3eec6e2bc7d",
)

load("@gmaven_rules//:gmaven.bzl", "gmaven_rules")
gmaven_rules()

android_sdk_repository(name = "androidsdk")

maven_jar(
    name = "com_squareup_okhttp3_okhttp",
    artifact = "com.squareup.okhttp3:okhttp:3.12.1",
    sha1 = "dc6d02e4e68514eff5631963e28ca7742ac69efe",
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