package com.github.johnnysc.practicetdd

class TwoFieldsObservable(private val observer: TwoFieldsObjectObserver) {
    private var twoFieldsObject: FieldsObject = FieldsObject.Initial()
    fun accept(name: String) {
        twoFieldsObject = (twoFieldsObject as FieldsObject.ToStringFieldsObject).map(name, observer)
    }

    fun accept(id: Int) {
        twoFieldsObject = (twoFieldsObject as FieldsObject.ToIntFieldsObject).map(id, observer)
    }
}
