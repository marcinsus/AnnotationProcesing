package com.example

import com.squareup.javapoet.*
import javax.lang.model.element.Element
import javax.lang.model.element.Modifier

const private val ORIGIN_FIELD = "original"
const private val OVERRIDE_FIELD = "override"

class ProvideServiceCodeGenerator : CodeGenerator {

    fun generate(element: Element): TypeSpec {
        val name = element.simpleName.toString()
        val newName = name + "Provider"
        val elementTypeName = TypeName.get(element.asType())
        val typeSpec = TypeSpec.classBuilder(newName)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addField(FieldSpec.builder(elementTypeName, ORIGIN_FIELD, Modifier.PRIVATE, Modifier.STATIC)
                        .build())
                .addField(FieldSpec.builder(elementTypeName, OVERRIDE_FIELD, Modifier.PRIVATE, Modifier.STATIC)
                        .build())
                .addMethod(createGetMethod(elementTypeName))
                .addMethod(createOverrideMethod(elementTypeName))
                .build()
        return typeSpec
    }

    private fun createGetMethod(elementTypeName: TypeName): MethodSpec {
        return MethodSpec.methodBuilder("get")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(elementTypeName)
                .addStatement("return $ORIGIN_FIELD")
                .build()
    }

    private fun createOverrideMethod(elementTypeName: TypeName): MethodSpec {
        val parameter = ParameterSpec.builder(elementTypeName, "service").build()
        return MethodSpec.methodBuilder("setOverride")
                .addParameter(parameter)
                .addStatement("$ORIGIN_FIELD = ${parameter.name}")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(TypeName.VOID)
                .build()
    }
}
