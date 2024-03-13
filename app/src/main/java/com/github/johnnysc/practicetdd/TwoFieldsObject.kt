package com.github.johnnysc.practicetdd

data class TwoFieldsObject(private val name: String, private val id: Int) : FieldsObject.Initial()

interface FieldsObject {
    interface ToIntFieldsObject : FieldsObject {
        fun map(arg: Int, observer: TwoFieldsObjectObserver): FieldsObject
    }

    interface ToStringFieldsObject : FieldsObject {
        fun map(arg: String, observer: TwoFieldsObjectObserver): FieldsObject
    }

    open class Initial : ToIntFieldsObject, ToStringFieldsObject {
        override fun map(arg: Int, observer: TwoFieldsObjectObserver) = IdField(arg)

        override fun map(arg: String, observer: TwoFieldsObjectObserver) = NameField(arg)
    }

    class IdField(private val id: Int) : ToStringFieldsObject {
        override fun map(arg: String, observer: TwoFieldsObjectObserver) =
            TwoFieldsObject(arg, id).also { observer.notify(it) }
    }

    class NameField(private val name: String) : ToIntFieldsObject {
        override fun map(arg: Int, observer: TwoFieldsObjectObserver) =
            TwoFieldsObject(name, arg).also { observer.notify(it) }
    }
}