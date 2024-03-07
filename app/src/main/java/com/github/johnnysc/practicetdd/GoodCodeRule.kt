package com.github.johnnysc.practicetdd

interface GoodCodeRule {
    fun isValid(text: String): Boolean

    abstract class Base(
        private val validatorsList: List<GoodCodeRuleWithCheck>
    ) : GoodCodeRule {
        override fun isValid(text: String): Boolean {
            val packageRegEx = Regex("package .+", setOf(RegexOption.MULTILINE))
            val importRegEx = Regex("import .+", setOf(RegexOption.MULTILINE))

            val textWithoutPackage = packageRegEx.replace(text, "").trim()
            val textWithoutImports = importRegEx.replace(textWithoutPackage, "").trim()
            val textWithoutEscapes = textWithoutImports.replace("\n", "")

            return validatorsList.first {
                it.canHandle(textWithoutEscapes)
            }.isValid(textWithoutEscapes)
        }

    }

    interface GoodCodeRuleWithCheck : GoodCodeRule {
        fun canHandle(text: String): Boolean
    }

    abstract class Abstract(
        private val checkPattern: String,
        private val validPattern: String,
        private val basePattern: String
    ) : GoodCodeRuleWithCheck {
        override fun canHandle(text: String): Boolean {
            val classRegEx = Regex(checkPattern, setOf(RegexOption.MULTILINE))

            return classRegEx.containsMatchIn(text)
        }

        override fun isValid(text: String): Boolean {
            val trueRegex = Regex(validPattern, setOf(RegexOption.MULTILINE))
            val falseRegex = Regex(basePattern, setOf(RegexOption.MULTILINE))

            return trueRegex.findAll(text).count() == falseRegex.findAll(text).count()
        }
    }

    class Encapsulation :
        Base(listOf(ClassEncapsulation(), AbstractClassEncapsulation()))

    abstract class AbstractEncapsulation(checkPattern: String, validPattern: String) :
        Abstract(checkPattern, validPattern, "val|var")

    class ClassEncapsulation :
        AbstractEncapsulation("^class .+", "private (val|var)")

    class AbstractClassEncapsulation :
        AbstractEncapsulation(
            "^abstract class .+",
            "(private|protected) (abstract )?(val|var)"
        )

    class ClassInheritance :
        Abstract("^class .+", "class .+:.+", "class .+")

    class InterfaceInheritance :
        Abstract("^interface .+", ":?.+", ":?.+")

    class AbstractClassInheritance :
        Abstract("^abstract class .+", ":?.+", ":?.+\\(.*\\).*") {
        override fun isValid(text: String): Boolean {
            val androidPattern = Regex(": ?.*(Activity|Fragment)")
            return androidPattern.containsMatchIn(text) || !super.isValid(text)
        }
    }

    class Inheritance :
        Base(listOf(ClassInheritance(), AbstractClassInheritance(), InterfaceInheritance()))

    class Functions : Base(listOf(InterfaceFunctions(), AbstractClassFunctions(), ClassFunctions()))

    class InterfaceFunctions : Abstract("^interface .+", "", "") {
        override fun isValid(text: String): Boolean {

            val defaultFunPattern = Regex("fun .*=.*")

            if(defaultFunPattern.containsMatchIn(text))
                return false

            print(defaultFunPattern.containsMatchIn(text))
            print(text)

            val paramsPattern = Regex("[a-zA-Z0-9]+ ?: ?[a-zA-Z0-9]*")

            if(paramsPattern.findAll(text).count() > 5)
                return false

            val funPattern = Regex("fun [a-zA-Z0-9]*")

            return funPattern.findAll(text).count() <= 5
        }
    }

    class ClassFunctions : Abstract("^class .+", "", "") {

        override fun isValid(text: String): Boolean {
            val privateFunPattern = Regex("private fun")

            if (privateFunPattern.containsMatchIn(text))
                return false

            val protectedFunPattern = Regex("protected fun")

            if (protectedFunPattern.containsMatchIn(text))
                return false

            val overrideFunPattern = Regex("override fun")
            val simpleFunPattern = Regex("fun")

            return overrideFunPattern.findAll(text).count() ==
                    simpleFunPattern.findAll(text).count()
        }
    }

    class AbstractClassFunctions : Abstract("^abstract class .+", "", ""){
        override fun isValid(text: String): Boolean {
            val privateFunPattern = Regex("private fun")

            if (privateFunPattern.containsMatchIn(text))
                return false

            val overrideFunPattern = Regex("(protected|override|protected abstract) fun")
            val simpleFunPattern = Regex("fun")

            return overrideFunPattern.findAll(text).count() ==
                    simpleFunPattern.findAll(text).count()
        }
    }

}
