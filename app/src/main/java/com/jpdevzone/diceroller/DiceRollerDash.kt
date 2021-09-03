package com.jpdevzone.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.jpdevzone.diceroller.databinding.DiceRollerDashBinding
import kotlin.random.Random

class DiceRollerDash : AppCompatActivity() {
    private lateinit var binding: DiceRollerDashBinding
    private lateinit var minusButton: ImageView
    private lateinit var plusButton: ImageView
    private lateinit var diceNumber: TextView
    private var clickCounter: Int = 1

    private lateinit var dice1: ImageView
    private lateinit var dice2: ImageView
    private lateinit var dice3: ImageView
    private lateinit var dice4: ImageView
    private lateinit var dice5: ImageView
    private lateinit var dice6: ImageView
    private lateinit var dice7: ImageView
    private lateinit var dice8: ImageView
    private lateinit var dice9: ImageView

    private lateinit var rollButton: ImageView
    private lateinit var diceSum: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DiceRollerDashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Controls
        minusButton = binding.btnMinus
        plusButton = binding.btnPlus
        diceNumber = binding.diceNumber

        //Dices
        dice1 = binding.dice1
        dice2 = binding.dice2
        dice3 = binding.dice3
        dice4 = binding.dice4
        dice5 = binding.dice5
        dice6 = binding.dice6
        dice7 = binding.dice7
        dice8 = binding.dice8
        dice9 = binding.dice9

        //Roll button
        rollButton = binding.btnRoll
        diceSum = binding.diceSum

        //Click minus button
        minusButton.setOnClickListener {
            if (diceSum.text != "0") {
                diceSum.text = "0"
            }
            decreaseDices()
        }

        //Click plus button
        plusButton.setOnClickListener {
            if (diceSum.text != "0") {
                diceSum.text = "0"
            }
            increaseDices()
        }

        //Roll dices on click
        rollButton.setOnClickListener {
            rollDices()
        }
    }

    private fun increaseDices() {
        clickCounter = (clickCounter + 1).coerceAtMost(9)
        diceNumber.text = clickCounter.toString()

        when (diceNumber.text) {
            "1" -> {
                invisible(dice1)
                invisible(dice2)
                invisible(dice3)
                invisible(dice4)
                visible(dice5)
                setDice(dice5, R.drawable.dice1)
                invisible(dice6)
                invisible(dice7)
                invisible(dice8)
                invisible(dice9)
            }
            "2" -> {
                invisible(dice1)
                invisible(dice2)
                invisible(dice3)
                visible(dice4)
                setDice(dice4, R.drawable.dice1)
                invisible(dice5)
                visible(dice6)
                setDice(dice6, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                invisible(dice7)
                invisible(dice8)
                invisible(dice9)
            }
            "3" -> {
                visible(dice1)
                setDice(dice1, R.drawable.dice1)
                invisible(dice2)
                invisible(dice3)
                invisible(dice4)
                visible(dice5)
                setDice(dice5, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                invisible(dice6)
                invisible(dice7)
                invisible(dice8)
                visible(dice9)
                setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            }
            "4" -> {
                visible(dice1)
                setDice(dice1, R.drawable.dice1)
                invisible(dice2)
                visible(dice3)
                setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                invisible(dice4)
                invisible(dice5)
                invisible(dice6)
                visible(dice7)
                setDice(dice7, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                invisible(dice8)
                visible(dice9)
                setDice(dice9, R.drawable.dice4)
            }
            "5" -> {
                visible(dice1)
                setDice(dice1, R.drawable.dice1)
                invisible(dice2)
                visible(dice3)
                setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                invisible(dice4)
                visible(dice5)
                setDice(dice5, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                invisible(dice6)
                visible(dice7)
                setDice(dice7, R.drawable.dice4)
                invisible(dice8)
                visible(dice9)
                setDice(dice9, R.drawable.dice5)
            }
            "6" -> {
                visible(dice1)
                setDice(dice1, R.drawable.dice1)
                invisible(dice2)
                visible(dice3)
                setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                visible(dice4)
                setDice(dice4, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                invisible(dice5)
                visible(dice6)
                setDice(dice6, R.drawable.dice4)
                visible(dice7)
                setDice(dice7, R.drawable.dice5)
                invisible(dice8)
                visible(dice9)
                setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
            }
            "7" -> {
                visible(dice1)
                setDice(dice1, R.drawable.dice1)
                invisible(dice2)
                visible(dice3)
                setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                visible(dice4)
                setDice(dice4, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                visible(dice5)
                setDice(dice5, R.drawable.dice4)
                visible(dice6)
                setDice(dice6, R.drawable.dice5)
                visible(dice7)
                setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                invisible(dice8)
                visible(dice9)
                setDice(dice9, R.drawable.dice1)
            }
            "8" -> {
                visible(dice1)
                setDice(dice1, R.drawable.dice1)
                visible(dice2)
                setDice(dice2, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                visible(dice3)
                setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                visible(dice4)
                setDice(dice4, R.drawable.dice4)
                invisible(dice5)
                visible(dice6)
                setDice(dice6, R.drawable.dice5)
                visible(dice7)
                setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                visible(dice8)
                setDice(dice8, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                visible(dice9)
                setDice(dice9, R.drawable.dice1)
            }
            "9" -> {
                visible(dice1)
                setDice(dice1, R.drawable.dice1)
                visible(dice2)
                setDice(dice2, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                visible(dice3)
                setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                visible(dice4)
                setDice(dice4, R.drawable.dice4)
                visible(dice5)
                setDice(dice5, R.drawable.dice5)
                visible(dice6)
                setDice(dice6, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                visible(dice7)
                setDice(dice7, R.drawable.dice1)
                visible(dice8)
                setDice(dice8, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                visible(dice9)
                setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            }
        }
    }

    private fun decreaseDices() {
        clickCounter = (clickCounter - 1).coerceAtLeast(1)
        diceNumber.text = clickCounter.toString()

        when (diceNumber.text) {
            "1" -> {
                invisible(dice1)
                invisible(dice2)
                invisible(dice3)
                invisible(dice4)
                visible(dice5)
                setDice(dice5, R.drawable.dice1)
                invisible(dice6)
                invisible(dice7)
                invisible(dice8)
                invisible(dice9)
            }
            "2" -> {
                invisible(dice1)
                invisible(dice2)
                invisible(dice3)
                visible(dice4)
                setDice(dice4, R.drawable.dice1)
                invisible(dice5)
                visible(dice6)
                setDice(dice6, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                invisible(dice7)
                invisible(dice8)
                invisible(dice9)
            }
            "3" -> {
                visible(dice1)
                setDice(dice1, R.drawable.dice1)
                invisible(dice2)
                invisible(dice3)
                invisible(dice4)
                visible(dice5)
                setDice(dice5, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                invisible(dice6)
                invisible(dice7)
                invisible(dice8)
                visible(dice9)
                setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            }
            "4" -> {
                visible(dice1)
                setDice(dice1, R.drawable.dice1)
                invisible(dice2)
                visible(dice3)
                setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                invisible(dice4)
                invisible(dice5)
                invisible(dice6)
                visible(dice7)
                setDice(dice7, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                invisible(dice8)
                visible(dice9)
                setDice(dice9, R.drawable.dice4)
            }
            "5" -> {
                visible(dice1)
                setDice(dice1, R.drawable.dice1)
                invisible(dice2)
                visible(dice3)
                setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                invisible(dice4)
                visible(dice5)
                setDice(dice5, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                invisible(dice6)
                visible(dice7)
                setDice(dice7, R.drawable.dice4)
                invisible(dice8)
                visible(dice9)
                setDice(dice9, R.drawable.dice5)
            }
            "6" -> {
                visible(dice1)
                setDice(dice1, R.drawable.dice1)
                invisible(dice2)
                visible(dice3)
                setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                visible(dice4)
                setDice(dice4, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                invisible(dice5)
                visible(dice6)
                setDice(dice6, R.drawable.dice4)
                visible(dice7)
                setDice(dice7, R.drawable.dice5)
                invisible(dice8)
                visible(dice9)
                setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
            }
            "7" -> {
                visible(dice1)
                setDice(dice1, R.drawable.dice1)
                invisible(dice2)
                visible(dice3)
                setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                visible(dice4)
                setDice(dice4, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                visible(dice5)
                setDice(dice5, R.drawable.dice4)
                visible(dice6)
                setDice(dice6, R.drawable.dice5)
                visible(dice7)
                setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                invisible(dice8)
                visible(dice9)
                setDice(dice9, R.drawable.dice1)
            }
            "8" -> {
                visible(dice1)
                setDice(dice1, R.drawable.dice1)
                visible(dice2)
                setDice(dice2, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                visible(dice3)
                setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                visible(dice4)
                setDice(dice4, R.drawable.dice4)
                invisible(dice5)
                visible(dice6)
                setDice(dice6, R.drawable.dice5)
                visible(dice7)
                setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                visible(dice8)
                setDice(dice8, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                visible(dice9)
                setDice(dice9, R.drawable.dice1)
            }
            "9" -> {
                visible(dice1)
                setDice(dice1, R.drawable.dice1)
                visible(dice2)
                setDice(dice2, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                visible(dice3)
                setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                visible(dice4)
                setDice(dice4, R.drawable.dice4)
                visible(dice5)
                setDice(dice5, R.drawable.dice5)
                visible(dice6)
                setDice(dice6, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                visible(dice7)
                setDice(dice7, R.drawable.dice1)
                visible(dice8)
                setDice(dice8, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                visible(dice9)
                setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            }
        }
    }

    private fun rollDices() {
        when (diceNumber.text) {
            "1"-> rollOneDice()
            "2" -> rollTwoDices()
            "3" -> rollThreeDices()
            "4" -> rollFourDices()
            "5" -> rollFiveDices()
            "6" -> rollSixDices()
            "7" -> rollSevenDices()
            "8" -> rollEightDices()
            "9" -> rollNineDices()
        }
    }

    private fun rollOneDice() {
        val randomInt1 = Random.nextInt(6)+1
        when (randomInt1) {
            1 -> setDice(dice5, R.drawable.dice1)
            2 -> setDice(dice5, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice5, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice5, R.drawable.dice4)
            5 -> setDice(dice5, R.drawable.dice5)
            6 -> setDice(dice5, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }
        diceSum.text = randomInt1.toString()
    }

    private fun rollTwoDices() {
        val randomInt1 = Random.nextInt(6)+1
        val randomInt2 = Random.nextInt(6)+1
        when (randomInt1) {
            1 -> setDice(dice4, R.drawable.dice1)
            2 -> setDice(dice4, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice4, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice4, R.drawable.dice4)
            5 -> setDice(dice4, R.drawable.dice5)
            6 -> setDice(dice4, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt2) {
            1 -> setDice(dice6, R.drawable.dice1)
            2 -> setDice(dice6, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice6, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice6, R.drawable.dice4)
            5 -> setDice(dice6, R.drawable.dice5)
            6 -> setDice(dice6, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }
        diceSum.text = (randomInt1+randomInt2).toString()
    }

    private fun rollThreeDices() {
        val randomInt1 = Random.nextInt(6)+1
        val randomInt2 = Random.nextInt(6)+1
        val randomInt3 = Random.nextInt(6)+1
        when (randomInt1) {
            1 -> setDice(dice1, R.drawable.dice1)
            2 -> setDice(dice1, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice1, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice1, R.drawable.dice4)
            5 -> setDice(dice1, R.drawable.dice5)
            6 -> setDice(dice1, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt2) {
            1 -> setDice(dice5, R.drawable.dice1)
            2 -> setDice(dice5, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice5, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice5, R.drawable.dice4)
            5 -> setDice(dice5, R.drawable.dice5)
            6 -> setDice(dice5, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt3) {
            1 -> setDice(dice9, R.drawable.dice1)
            2 -> setDice(dice9, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice9, R.drawable.dice4)
            5 -> setDice(dice9, R.drawable.dice5)
            6 -> setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }
        diceSum.text = (randomInt1+randomInt2+randomInt3).toString()
    }

    private fun rollFourDices() {
        val randomInt1 = Random.nextInt(6)+1
        val randomInt2 = Random.nextInt(6)+1
        val randomInt3 = Random.nextInt(6)+1
        val randomInt4 = Random.nextInt(6)+1
        when (randomInt1) {
            1 -> setDice(dice1, R.drawable.dice1)
            2 -> setDice(dice1, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice1, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice1, R.drawable.dice4)
            5 -> setDice(dice1, R.drawable.dice5)
            6 -> setDice(dice1, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt2) {
            1 -> setDice(dice3, R.drawable.dice1)
            2 -> setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice3, R.drawable.dice4)
            5 -> setDice(dice3, R.drawable.dice5)
            6 -> setDice(dice3, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt3) {
            1 -> setDice(dice7, R.drawable.dice1)
            2 -> setDice(dice7, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice7, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice7, R.drawable.dice4)
            5 -> setDice(dice7, R.drawable.dice5)
            6 -> setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt4) {
            1 -> setDice(dice9, R.drawable.dice1)
            2 -> setDice(dice9, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice9, R.drawable.dice4)
            5 -> setDice(dice9, R.drawable.dice5)
            6 -> setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }
        diceSum.text = (randomInt1+randomInt2+randomInt3+randomInt4).toString()
    }

    private fun rollFiveDices() {
        val randomInt1 = Random.nextInt(6)+1
        val randomInt2 = Random.nextInt(6)+1
        val randomInt3 = Random.nextInt(6)+1
        val randomInt4 = Random.nextInt(6)+1
        val randomInt5 = Random.nextInt(6)+1
        when (randomInt1) {
            1 -> setDice(dice1, R.drawable.dice1)
            2 -> setDice(dice1, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice1, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice1, R.drawable.dice4)
            5 -> setDice(dice1, R.drawable.dice5)
            6 -> setDice(dice1, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt2) {
            1 -> setDice(dice3, R.drawable.dice1)
            2 -> setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice3, R.drawable.dice4)
            5 -> setDice(dice3, R.drawable.dice5)
            6 -> setDice(dice3, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt3) {
            1 -> setDice(dice5, R.drawable.dice1)
            2 -> setDice(dice5, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice5, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice5, R.drawable.dice4)
            5 -> setDice(dice5, R.drawable.dice5)
            6 -> setDice(dice5, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt4) {
            1 -> setDice(dice7, R.drawable.dice1)
            2 -> setDice(dice7, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice7, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice7, R.drawable.dice4)
            5 -> setDice(dice7, R.drawable.dice5)
            6 -> setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt5) {
            1 -> setDice(dice9, R.drawable.dice1)
            2 -> setDice(dice9, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice9, R.drawable.dice4)
            5 -> setDice(dice9, R.drawable.dice5)
            6 -> setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }
        diceSum.text = (randomInt1+randomInt2+randomInt3+randomInt4+randomInt5).toString()
    }

    private fun rollSixDices() {
        val randomInt1 = Random.nextInt(6)+1
        val randomInt2 = Random.nextInt(6)+1
        val randomInt3 = Random.nextInt(6)+1
        val randomInt4 = Random.nextInt(6)+1
        val randomInt5 = Random.nextInt(6)+1
        val randomInt6 = Random.nextInt(6)+1
        when (randomInt1) {
            1 -> setDice(dice1, R.drawable.dice1)
            2 -> setDice(dice1, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice1, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice1, R.drawable.dice4)
            5 -> setDice(dice1, R.drawable.dice5)
            6 -> setDice(dice1, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt2) {
            1 -> setDice(dice3, R.drawable.dice1)
            2 -> setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice3, R.drawable.dice4)
            5 -> setDice(dice3, R.drawable.dice5)
            6 -> setDice(dice3, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt3) {
            1 -> setDice(dice4, R.drawable.dice1)
            2 -> setDice(dice4, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice4, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice4, R.drawable.dice4)
            5 -> setDice(dice4, R.drawable.dice5)
            6 -> setDice(dice4, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt4) {
            1 -> setDice(dice6, R.drawable.dice1)
            2 -> setDice(dice6, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice6, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice6, R.drawable.dice4)
            5 -> setDice(dice6, R.drawable.dice5)
            6 -> setDice(dice6, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt5) {
            1 -> setDice(dice7, R.drawable.dice1)
            2 -> setDice(dice7, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice7, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice7, R.drawable.dice4)
            5 -> setDice(dice7, R.drawable.dice5)
            6 -> setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt6) {
            1 -> setDice(dice9, R.drawable.dice1)
            2 -> setDice(dice9, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice9, R.drawable.dice4)
            5 -> setDice(dice9, R.drawable.dice5)
            6 -> setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }
        diceSum.text = (randomInt1+randomInt2+randomInt3+randomInt4+randomInt5+randomInt6).toString()
    }

    private fun rollSevenDices() {
        val randomInt1 = Random.nextInt(6)+1
        val randomInt2 = Random.nextInt(6)+1
        val randomInt3 = Random.nextInt(6)+1
        val randomInt4 = Random.nextInt(6)+1
        val randomInt5 = Random.nextInt(6)+1
        val randomInt6 = Random.nextInt(6)+1
        val randomInt7 = Random.nextInt(6)+1
        when (randomInt1) {
            1 -> setDice(dice1, R.drawable.dice1)
            2 -> setDice(dice1, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice1, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice1, R.drawable.dice4)
            5 -> setDice(dice1, R.drawable.dice5)
            6 -> setDice(dice1, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt2) {
            1 -> setDice(dice3, R.drawable.dice1)
            2 -> setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice3, R.drawable.dice4)
            5 -> setDice(dice3, R.drawable.dice5)
            6 -> setDice(dice3, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt3) {
            1 -> setDice(dice4, R.drawable.dice1)
            2 -> setDice(dice4, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice4, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice4, R.drawable.dice4)
            5 -> setDice(dice4, R.drawable.dice5)
            6 -> setDice(dice4, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt4) {
            1 -> setDice(dice5, R.drawable.dice1)
            2 -> setDice(dice5, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice5, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice5, R.drawable.dice4)
            5 -> setDice(dice5, R.drawable.dice5)
            6 -> setDice(dice5, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt5) {
            1 -> setDice(dice6, R.drawable.dice1)
            2 -> setDice(dice6, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice6, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice6, R.drawable.dice4)
            5 -> setDice(dice6, R.drawable.dice5)
            6 -> setDice(dice6, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt6) {
            1 -> setDice(dice7, R.drawable.dice1)
            2 -> setDice(dice7, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice7, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice7, R.drawable.dice4)
            5 -> setDice(dice7, R.drawable.dice5)
            6 -> setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt7) {
            1 -> setDice(dice9, R.drawable.dice1)
            2 -> setDice(dice9, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice9, R.drawable.dice4)
            5 -> setDice(dice9, R.drawable.dice5)
            6 -> setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }
        diceSum.text = (randomInt1+randomInt2+randomInt3+randomInt4+randomInt5+randomInt6+randomInt7).toString()
    }

    private fun rollEightDices() {
        val randomInt1 = Random.nextInt(6)+1
        val randomInt2 = Random.nextInt(6)+1
        val randomInt3 = Random.nextInt(6)+1
        val randomInt4 = Random.nextInt(6)+1
        val randomInt5 = Random.nextInt(6)+1
        val randomInt6 = Random.nextInt(6)+1
        val randomInt7 = Random.nextInt(6)+1
        val randomInt8 = Random.nextInt(6)+1
        when (randomInt1) {
            1 -> setDice(dice1, R.drawable.dice1)
            2 -> setDice(dice1, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice1, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice1, R.drawable.dice4)
            5 -> setDice(dice1, R.drawable.dice5)
            6 -> setDice(dice1, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt2) {
            1 -> setDice(dice2, R.drawable.dice1)
            2 -> setDice(dice2, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice2, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice2, R.drawable.dice4)
            5 -> setDice(dice2, R.drawable.dice5)
            6 -> setDice(dice2, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt3) {
            1 -> setDice(dice3, R.drawable.dice1)
            2 -> setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice3, R.drawable.dice4)
            5 -> setDice(dice3, R.drawable.dice5)
            6 -> setDice(dice3, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt4) {
            1 -> setDice(dice4, R.drawable.dice1)
            2 -> setDice(dice4, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice4, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice4, R.drawable.dice4)
            5 -> setDice(dice4, R.drawable.dice5)
            6 -> setDice(dice4, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt5) {
            1 -> setDice(dice6, R.drawable.dice1)
            2 -> setDice(dice6, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice6, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice6, R.drawable.dice4)
            5 -> setDice(dice6, R.drawable.dice5)
            6 -> setDice(dice6, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt6) {
            1 -> setDice(dice7, R.drawable.dice1)
            2 -> setDice(dice7, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice7, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice7, R.drawable.dice4)
            5 -> setDice(dice7, R.drawable.dice5)
            6 -> setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt7) {
            1 -> setDice(dice8, R.drawable.dice1)
            2 -> setDice(dice8, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice8, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice8, R.drawable.dice4)
            5 -> setDice(dice8, R.drawable.dice5)
            6 -> setDice(dice8, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt8) {
            1 -> setDice(dice9, R.drawable.dice1)
            2 -> setDice(dice9, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice9, R.drawable.dice4)
            5 -> setDice(dice9, R.drawable.dice5)
            6 -> setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }
        diceSum.text = (randomInt1+randomInt2+randomInt3+randomInt4+randomInt5+randomInt6+randomInt7+randomInt8).toString()
    }

    private fun rollNineDices() {
        val randomInt1 = Random.nextInt(6)+1
        val randomInt2 = Random.nextInt(6)+1
        val randomInt3 = Random.nextInt(6)+1
        val randomInt4 = Random.nextInt(6)+1
        val randomInt5 = Random.nextInt(6)+1
        val randomInt6 = Random.nextInt(6)+1
        val randomInt7 = Random.nextInt(6)+1
        val randomInt8 = Random.nextInt(6)+1
        val randomInt9 = Random.nextInt(6)+1
        when (randomInt1) {
            1 -> setDice(dice1, R.drawable.dice1)
            2 -> setDice(dice1, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice1, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice1, R.drawable.dice4)
            5 -> setDice(dice1, R.drawable.dice5)
            6 -> setDice(dice1, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt2) {
            1 -> setDice(dice2, R.drawable.dice1)
            2 -> setDice(dice2, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice2, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice2, R.drawable.dice4)
            5 -> setDice(dice2, R.drawable.dice5)
            6 -> setDice(dice2, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt3) {
            1 -> setDice(dice3, R.drawable.dice1)
            2 -> setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice3, R.drawable.dice4)
            5 -> setDice(dice3, R.drawable.dice5)
            6 -> setDice(dice3, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt4) {
            1 -> setDice(dice4, R.drawable.dice1)
            2 -> setDice(dice4, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice4, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice4, R.drawable.dice4)
            5 -> setDice(dice4, R.drawable.dice5)
            6 -> setDice(dice4, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt5) {
            1 -> setDice(dice5, R.drawable.dice1)
            2 -> setDice(dice5, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice5, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice5, R.drawable.dice4)
            5 -> setDice(dice5, R.drawable.dice5)
            6 -> setDice(dice5, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt6) {
            1 -> setDice(dice6, R.drawable.dice1)
            2 -> setDice(dice6, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice6, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice6, R.drawable.dice4)
            5 -> setDice(dice6, R.drawable.dice5)
            6 -> setDice(dice6, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt7) {
            1 -> setDice(dice7, R.drawable.dice1)
            2 -> setDice(dice7, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice7, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice7, R.drawable.dice4)
            5 -> setDice(dice7, R.drawable.dice5)
            6 -> setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt8) {
            1 -> setDice(dice8, R.drawable.dice1)
            2 -> setDice(dice8, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice8, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice8, R.drawable.dice4)
            5 -> setDice(dice8, R.drawable.dice5)
            6 -> setDice(dice8, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }

        when (randomInt9) {
            1 -> setDice(dice9, R.drawable.dice1)
            2 -> setDice(dice9, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
            3 -> setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
            4 -> setDice(dice9, R.drawable.dice4)
            5 -> setDice(dice9, R.drawable.dice5)
            6 -> setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
        }
        diceSum.text = (randomInt1+randomInt2+randomInt3+randomInt4+randomInt5+randomInt6+randomInt7+randomInt8+randomInt9).toString()
    }

    private fun visible(view: View) {
        view.visibility = View.VISIBLE
    }

    private fun invisible(view: View) {
        view.visibility = View.INVISIBLE
    }

    private fun setDice(view: ImageView, layout: Int) {
        view.setImageResource(layout)
    }

}