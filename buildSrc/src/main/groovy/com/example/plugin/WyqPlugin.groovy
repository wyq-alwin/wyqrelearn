package com.example.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.gradle.BaseExtension


class WyqPlugin implements Plugin<Project> {

    void apply(Project target) {
        def extension = target.extensions.create("wyq", WyqExtension)
        target.afterEvaluate {
            println(("hello!! ${extension.name}"))
        }
        def transform = new WYQTransform()
        def baseExtension = target.extensions.getByType(BaseExtension)
        println(baseExtension)
        baseExtension.registerTransform(transform)
    }
}
