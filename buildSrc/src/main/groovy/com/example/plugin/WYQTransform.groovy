package com.example.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils


class WYQTransform extends Transform {

    java.lang.String getName() {
        return "wyq123"
    }

    @java.lang.Override
    java.util.Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @java.lang.Override
    java.util.Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @java.lang.Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        def inputs = transformInvocation.inputs
        def outputProvider = transformInvocation.outputProvider
        inputs.each {
            it.jarInputs.each {
                File dest = outputProvider.getContentLocation(it.name, it.contentTypes, it.scopes, Format.JAR)
                println "Jar: ${it.file}, Dest: ${dest}"
                FileUtils.copyFile(it.file, dest)
            }
            it.directoryInputs.each {
                File dest = outputProvider.getContentLocation(it.name, it.contentTypes, it.scopes, Format.DIRECTORY)
                println "Dir: ${it.file}, Dest: ${dest}"
                FileUtils.copyDirectory(it.file, dest)
            }
        }
    }
}