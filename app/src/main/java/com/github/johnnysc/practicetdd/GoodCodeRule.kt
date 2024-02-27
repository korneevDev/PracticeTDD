package com.github.johnnysc.practicetdd

interface GoodCodeRule {
    fun isValid(text: String): Boolean

    @Suppress("NAME_SHADOWING")
    class Encapsulation(
        private val `class`: CodeRuleWithCheck = Class(),
        private val abstractClass: CodeRuleWithCheck = AbstractClass(),
    ) : GoodCodeRule {
        override fun isValid(text: String): Boolean {
            val packageRegEx = Regex("package .+", setOf(RegexOption.MULTILINE))

            val text = packageRegEx.replace(text, "").trim()

            return if (`class`.check(text))
                `class`.isValid(text)
            else
                abstractClass.isValid(text)
        }

    }

    interface CodeRuleWithCheck : GoodCodeRule {
        fun check(text: String): Boolean
    }

    abstract class Abstract(
        private val checkPattern : String,
        private val validPattern : String
    ) : CodeRuleWithCheck{
        override fun check(text: String): Boolean {
            val classRegEx = Regex(checkPattern, setOf(RegexOption.MULTILINE))

            return classRegEx.containsMatchIn(text)
        }

        override fun isValid(text: String): Boolean {
            val trueRegex = Regex(validPattern, setOf(RegexOption.MULTILINE))
            val falseRegex = Regex("val|var", setOf(RegexOption.MULTILINE))

            return trueRegex.findAll(text).count() == falseRegex.findAll(text).count()
        }
    }
    class Class : Abstract("^class .+", "private (val|var)")
    class AbstractClass : Abstract("^abstract class .+", "(private|protected) (abstract )?(val|var)")

}
