package com.github.johnnysc.practicetdd

interface MyStack<T> {

    fun pop(): T
    fun push(item: T)

    open class LIFO<T>(
        private val maxCount: Int
    ) : MyStack<T>{
        protected val stack = arrayOfNulls<Any?>(maxCount)
        private var lastIndex = 0
        init {
            if (maxCount < 1)
                throw IllegalStateException()

        }

        override fun pop(): T {
            if(lastIndex <= 0){
                throw IllegalStateException("Stack is empty")
            }

            return stack[--lastIndex] as T
        }

        override fun push(item: T) {
            try {
                stack[lastIndex++] = item
            } catch (e: Exception){
                throw IllegalStateException("Stack overflow exception, maximum is $maxCount")
            }
        }
    }

    class FIFO<T>(
        maxCount: Int
    ) : LIFO<T>(maxCount){

        override fun pop(): T {
            super.pop()
            return stack[0].also {
                for(i in 1 until stack.size)
                    stack[i-1] = stack[i]
            } as T
        }
    }
}
