package com.github.johnnysc.practicetdd

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan

interface MarkDown {
    interface Parser {
        fun parse(s: String): ResultItem

        open class Base(
            private val type: String,
            private val delimiter: String
        ) : Parser {
            override fun parse(s: String): ResultItem {
                val pattern = delimiter.replace("*", "\\*")

                val matches = Regex("$pattern[a-zA-Z0-9 ]*$pattern").findAll(s)

                var bias = 0
                var currentString = s
                val resultList = mutableListOf<ResultItem.StringAndIndex>()
                matches.iterator().forEach {
                    val valueWithoutDelimiter = it.value.replace(delimiter, "")
                    if (valueWithoutDelimiter.isNotEmpty())
                        resultList.add(
                            ResultItem.StringAndIndex(
                                valueWithoutDelimiter,
                                it.range.first - bias
                            )
                        )
                    bias += it.value.length - valueWithoutDelimiter.length
                    currentString = currentString.replace(it.value, valueWithoutDelimiter)
                }
                return ResultItem.Base(type, currentString, resultList)

            }

        }

        class OneSignDelimiter(
            text: String,
            delimiter: Char
        ) : Base(text, delimiter.toString())

    }

    interface ResultItem {
        fun formattedText(): CharSequence

        data class Base(
            private val color: String,
            private val text: String,
            private val parsedItems: List<StringAndIndex>
        ) : ResultItem {
            override fun formattedText(): CharSequence {
                val span = SpannableStringBuilder(text)
                parsedItems.forEach {
                    it.setSpan(span, color)
                }

                return span
            }
        }

        data class StringAndIndex(
            private val string: String,
            private val index: Int
        ) {
            fun setSpan(span: SpannableStringBuilder, color: String) {
                span.setSpan(
                    ForegroundColorSpan(Color.parseColor(color)),
                    index,
                    index + string.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

    }

}
