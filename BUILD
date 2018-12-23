load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library", "kt_android_library")

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
        "@com_squareup_okhttp3_okhttp//jar",
        "@com_squareup_okio_okio//jar",
        "@io_reactivex_rxjava2_rxjava2//jar",
        "@org_reactivestreams_reactive_streams//jar",
        "@io_reactivex_rxjava2_rxandroid//aar",

        "@androidx_recyclerview_recyclerview_1_0_0//aar",
        "@androidx_core_core_1_0_1//aar",
    ]  
)