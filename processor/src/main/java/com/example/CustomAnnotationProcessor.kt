package com.example

import com.example.annotation.CustomAnnotation
import java.io.IOException
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@SupportedAnnotationTypes("com.example.annotation.CustomAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
open class CustomAnnotationProcessor : AbstractProcessor() {
    override fun process(set: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        val builder = StringBuilder()
                .append("package com.example.annotationprocessor.generated;\n\n")
                .append("public class GeneratedClass {\n\n") // open class
                .append("\tpublic String getMessage() {\n") // open method
                .append("\t\treturn \"")


        // for each javax.lang.model.element.Element annotated with the CustomAnnotation
        for (element in roundEnv.getElementsAnnotatedWith(CustomAnnotation::class.java)) {
            val objectType = element.getSimpleName().toString()
            // this is appending to the return statement
            builder.append(objectType).append(" is alive!\\n")
        }


        builder.append("\";\n") // end return
                .append("\t}\n") // close method
                .append("}\n") // close class


        try { // write the file
            val source = processingEnv.filer.createSourceFile("com.stablekernel.annotationprocessor.generated.GeneratedClass")


            val writer = source.openWriter()
            writer.write(builder.toString())
            writer.flush()
            writer.close()
        } catch (e: IOException) {
            // Note: calling e.printStackTrace() will print IO errors
            // that occur from the file already existing after its first run, this is normal
        }


        return true
    }
}
