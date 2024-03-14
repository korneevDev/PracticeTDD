package com.github.johnnysc.practicetdd

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.Button

@SuppressLint("AppCompatCustomView")
class ChoiceButton : Button, Choice {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private lateinit var block: () -> Unit
    override fun init(mediator: MediatorChoice, block: () -> Unit) {
        this.block = block
        this.setOnClickListener {
            mediator.change(this, block)
        }
    }

    override fun isChosen(): Boolean = this.isEnabled

    override fun chose() {
        this.isEnabled = false
        this.block.invoke()
    }

    override fun rollback() {
        this.isEnabled = true
    }

}