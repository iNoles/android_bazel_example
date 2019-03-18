load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_android_library")
load("@rules_jvm_external//:defs.bzl", "artifact")

PACKAGE = "com.example.bazel"
MANIFEST = "AndroidManifest.xml"
MANIFEST_VALUES = {"PACKAGE": PACKAGE}

android_binary(
    name = "app",
    custom_package = PACKAGE,
    manifest = MANIFEST,
    manifest_values = MANIFEST_VALUES,
    deps = [
        ":main",
    ],
)

kt_android_library(
    name = "main",
    srcs = glob(["src/**/*.kt"]),
    custom_package = PACKAGE,
    manifest = MANIFEST,
    resource_files = glob(["res/**/*"]),
    deps = [
        artifact("com.squareup.okhttp3:okhttp"),
        artifact("io.reactivex.rxjava2:rxjava"),
        artifact("io.reactivex.rxjava2:rxandroid"),

        artifact("androidx.core:core:aar"),
        artifact("androidx.recyclerview:recyclerview"),

        artifact("org.reactivestreams:reactive-streams")
    ],
)