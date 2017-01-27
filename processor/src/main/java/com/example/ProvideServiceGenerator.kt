package com.example

import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import javax.lang.model.element.Element
import javax.lang.model.element.Modifier

open class ProvideServiceCodeGenerator : CodeGenerator {
    fun generate(element: Element): TypeSpec {
        val name = element.simpleName.toString()
        val newName = name + "Provider"
        val typeSpec = TypeSpec.classBuilder(newName)
                .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                .addField(FieldSpec.builder(TypeName.get(element.asType()), "original", Modifier.PRIVATE)
                        .build())
                .addMethod(createGetMethod())
                .addMethod(createOverrideMethod())
                .build()
        return typeSpec
    }

    private fun createGetMethod(): MethodSpec {
        return MethodSpec.methodBuilder("get")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(TypeName.VOID).build()
    }

    private fun createOverrideMethod(): MethodSpec {
        return MethodSpec.methodBuilder("override")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(TypeName.VOID).build()
    }
}
