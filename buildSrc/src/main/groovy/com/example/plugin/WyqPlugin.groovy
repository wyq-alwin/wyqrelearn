package com.example.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project


class WyqPlugin implements Plugin<Project> {

    void apply(Project target) {
        def extension = target.extensions.create("wyq", WyqExtension)
        target.afterEvaluate {
            println(("hello!! ${extension.name}"))
        }
    }
}
