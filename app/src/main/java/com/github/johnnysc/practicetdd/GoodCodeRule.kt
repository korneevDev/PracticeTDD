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

            return validatorsList.first {
                it.canHandle(textWithoutImports)
            }.isValid(textWithoutImports)
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

}
