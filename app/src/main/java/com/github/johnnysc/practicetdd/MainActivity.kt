package com.github.johnnysc.practicetdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediatorChoice = MediatorChoice.Base()

        val firstButton = findViewById<ChoiceButton>(R.id.firstChoiceButton)
        val secondButton = findViewById<ChoiceButton>(R.id.secondChoiceButton)
        val thirdButton = findViewById<ChoiceButton>(R.id.thirdChoiceButton)

        val saveButton = findViewById<Button>(R.id.saveButton)

        val block : ()-> Unit = {
            saveButton.isEnabled = true
        }

        firstButton.init(mediatorChoice, block)
        secondButton.init(mediatorChoice, block)
        thirdButton.init(mediatorChoice, block)
    }
}