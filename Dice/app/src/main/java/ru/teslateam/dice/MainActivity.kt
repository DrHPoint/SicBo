package ru.teslateam.dice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess
import android.widget.AdapterView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mode: Array<String> = resources.getStringArray(R.array.dice_mode)
        val stavka: Array<String> = resources.getStringArray(R.array.Stavka)
        var modeDice = 4
        var stavkaInt = 100
        var value = 10000

        fun spinnerSelect(x: String): Int {
            when (x) {
                mode[0] -> return 4
                mode[1] -> return 6
                mode[2] -> return 10
                mode[3] -> return 12
                else -> return 4
            }
        }

        val adapter = ArrayAdapter(this, R.layout.spinner, mode)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View?, position: Int, id: Long){
                modeDice = spinnerSelect(parent.getItemAtPosition(position).toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>){
            }
        }

        fun Game(but: Int) {
            var firstDice = Random.nextInt(1, modeDice)
            var secondDice = Random.nextInt(1, modeDice)
            var thirdDice = Random.nextInt(1, modeDice)
            if ((firstDice == secondDice)&&(firstDice == thirdDice)) {
                textView2.text = "Победа казино"
                value -= stavkaInt
                textView.text = "Счет:" + value.toString()
                textView3.text = "Выпало: " + firstDice + " " + secondDice + " " + thirdDice
            } else if ((firstDice + secondDice + thirdDice) < (modeDice * 3 / 2)) {
                if (but == 1) {
                    value += stavkaInt
                    textView2.text = "Вы победили"
                    textView.text = "Счет:" + value.toString()
                    textView3.text = "Выпало: " + firstDice + " " + secondDice + " " + thirdDice
                }
                else {
                    value -= stavkaInt
                    textView2.text = "Вы проиграли"
                    textView.text = "Счет:" + value.toString()
                    textView3.text = "Выпало: " + firstDice + " " + secondDice + " " + thirdDice
                }
            } else {
                if (but == 2) {
                    value += stavkaInt
                    textView2.text = "Вы победили"
                    textView.text = "Счет:" + value.toString()
                    textView3.text = "Выпало: " + firstDice + " " + secondDice + " " + thirdDice
                }
                else {
                    value -= stavkaInt
                    textView2.text = "Вы проиграли"
                    textView.text = "Счет:" + value.toString()
                    textView3.text = "Выпало: " + firstDice + " " + secondDice + " " + thirdDice
                }
            }
        }

        button3.setOnClickListener {
            Game(1)
        }

        button4.setOnClickListener {
            Game(2)
        }





    }
}
