package com.jpdevzone.diceroller.ui

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.jpdevzone.diceroller.Constants
import com.jpdevzone.diceroller.R
import com.jpdevzone.diceroller.databinding.DiceRollerBinding
import es.dmoral.toasty.Toasty
import kotlin.random.Random


class DiceRoller : AppCompatActivity() {
    private lateinit var binding: DiceRollerBinding
    private lateinit var muteButton: ImageView
    private lateinit var minusButton: ImageView
    private lateinit var plusButton: ImageView
    private lateinit var diceNumber: TextView
    private lateinit var themePicker: ImageView
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

    private lateinit var soundPool: SoundPool
    private var rollSound: Int? = null

    private var mInterstitialAd: InterstitialAd? = null
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DiceRollerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Load shared prefs
        loadData()

        //Controls
        muteButton = binding.btnMute
        minusButton = binding.btnMinus
        plusButton = binding.btnPlus
        diceNumber = binding.diceNumber
        themePicker = binding.btnTheme

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

        //Click mute button
        muteUnmuteSound()

        //Click minus button
        minusButton.setOnClickListener {
            if (diceSum.text != "0") {
                diceSum.text = "0"
            }
            decreaseDices()
            showAd()
        }

        //Click plus button
        plusButton.setOnClickListener {
            if (diceSum.text != "0") {
                diceSum.text = "0"
            }
            increaseDices()
            showAd()
        }

        //Select theme
        themePicker.setOnClickListener {
//            val intent = Intent(this, DiceRollerDashDark::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//            overridePendingTransition(0, 0)
//            startActivity(intent)
//            finish()
        }

        //Roll dices on click
        rollButton.setOnClickListener {
            rollDices()
        }

        MobileAds.initialize(this) {}
        createPersonalizedAdd()
    }

    @Suppress("DEPRECATION")
    private fun muteUnmuteSound() {
        soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttributes)
                .build()
        } else {
            SoundPool(1, AudioManager.STREAM_MUSIC, 0)
        }

        rollSound = soundPool.load(this, R.raw.roll_sound, 1)
        Constants.isClicked = false

        muteButton.setOnClickListener {
            when (Constants.isClicked) {
                false -> {
                    muteButton.setImageResource(R.drawable.bg_btn_mute)
                    Constants.isClicked = true
                    rollSound = null
                }
                true -> {
                    muteButton.setImageResource(R.drawable.bg_btn_unmute)
                    Constants.isClicked = false
                    rollSound = soundPool.load(this, R.raw.roll_sound, 1)
                }
            }
        }
    }

    private fun showAd() {
        Constants.adCounter++
        when (Constants.adCounter % 5 == 0) {
            true -> {
                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(this)
                    createPersonalizedAdd()
                } else {
                    createPersonalizedAdd()
                }
            }
        }
    }

    private fun createPersonalizedAdd() {
        val adRequest = AdRequest.Builder().build()
        createInterstitialAdd(adRequest)
    }

    private fun createInterstitialAdd(adRequest: AdRequest) {
        InterstitialAd.load(this,"ca-app-pub-7588987461083278/9903474518", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })
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
        rollSound?.let { soundPool.play(it, 1f, 1f, 0, 0, 1f) }
    }

    private fun rollOneDice() {
        val randomInt1 = Random.nextInt(6)+1
        when (randomInt1) {
            1 -> {
                setDice(dice5, R.drawable.dice1)
                animate(dice5)
            }
            2 -> {
                setDice(dice5, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice5)
            }
            3 -> {
                setDice(dice5, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice5)
            }
            4 -> {
                setDice(dice5, R.drawable.dice4)
                animate(dice5)
            }
            5 -> {
                setDice(dice5, R.drawable.dice5)
                animate(dice5)
            }
            6 -> {
                setDice(dice5, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice5)
            }
        }
        diceSum.text = randomInt1.toString()
    }

    private fun rollTwoDices() {
        val randomInt1 = Random.nextInt(6)+1
        val randomInt2 = Random.nextInt(6)+1
        when (randomInt1) {
            1 -> {
                setDice(dice4, R.drawable.dice1)
                animate(dice4)
            }
            2 -> {
                setDice(dice4, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice4)
            }
            3 -> {
                setDice(dice4, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice4)
            }
            4 -> {
                setDice(dice4, R.drawable.dice4)
                animate(dice4)
            }
            5 -> {
                setDice(dice4, R.drawable.dice5)
                animate(dice4)
            }
            6 -> {
                setDice(dice4, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice4)
            }
        }

        when (randomInt2) {
            1 -> {
                setDice(dice6, R.drawable.dice1)
                animate(dice6)
            }
            2 -> {
                setDice(dice6, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice6)
            }
            3 -> {
                setDice(dice6, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice6)
            }
            4 -> {
                setDice(dice6, R.drawable.dice4)
                animate(dice6)
            }
            5 -> {
                setDice(dice6, R.drawable.dice5)
                animate(dice6)
            }
            6 -> {
                setDice(dice6, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice6)
            }
        }
        diceSum.text = (randomInt1+randomInt2).toString()
    }

    private fun rollThreeDices() {
        val randomInt1 = Random.nextInt(6)+1
        val randomInt2 = Random.nextInt(6)+1
        val randomInt3 = Random.nextInt(6)+1
        when (randomInt1) {
            1 -> {
                setDice(dice1, R.drawable.dice1)
                animate(dice1)
            }
            2 -> {
                setDice(dice1, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice1)
            }
            3 -> {
                setDice(dice1, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice1)
            }
            4 -> {
                setDice(dice1, R.drawable.dice4)
                animate(dice1)
            }
            5 -> {
                setDice(dice1, R.drawable.dice5)
                animate(dice1)
            }
            6 -> {
                setDice(dice1, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice1)
            }
        }

        when (randomInt2) {
            1 -> {
                setDice(dice5, R.drawable.dice1)
                animate(dice5)
            }
            2 -> {
                setDice(dice5, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice5)
            }
            3 -> {
                setDice(dice5, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice5)
            }
            4 -> {
                setDice(dice5, R.drawable.dice4)
                animate(dice5)
            }
            5 -> {
                setDice(dice5, R.drawable.dice5)
                animate(dice5)
            }
            6 -> {
                setDice(dice5, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice5)
            }
        }

        when (randomInt3) {
            1 -> {
                setDice(dice9, R.drawable.dice1)
                animate(dice9)
            }
            2 -> {
                setDice(dice9, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice9)
            }
            3 -> {
                setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice9)
            }
            4 -> {
                setDice(dice9, R.drawable.dice4)
                animate(dice9)
            }
            5 -> {
                setDice(dice9, R.drawable.dice5)
                animate(dice9)
            }
            6 -> {
                setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice9)
            }
        }
        diceSum.text = (randomInt1+randomInt2+randomInt3).toString()
    }

    private fun rollFourDices() {
        val randomInt1 = Random.nextInt(6)+1
        val randomInt2 = Random.nextInt(6)+1
        val randomInt3 = Random.nextInt(6)+1
        val randomInt4 = Random.nextInt(6)+1
        when (randomInt1) {
            1 -> {
                setDice(dice1, R.drawable.dice1)
                animate(dice1)
            }
            2 -> {
                setDice(dice1, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice1)
            }
            3 -> {
                setDice(dice1, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice1)
            }
            4 -> {
                setDice(dice1, R.drawable.dice4)
                animate(dice1)
            }
            5 -> {
                setDice(dice1, R.drawable.dice5)
                animate(dice1)
            }
            6 -> {
                setDice(dice1, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice1)
            }
        }

        when (randomInt2) {
            1 -> {
                setDice(dice3, R.drawable.dice1)
                animate(dice3)
            }
            2 -> {
                setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice3)
            }
            3 -> {
                setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice3)
            }
            4 -> {
                setDice(dice3, R.drawable.dice4)
                animate(dice3)
            }
            5 -> {
                setDice(dice3, R.drawable.dice5)
                animate(dice3)
            }
            6 -> {
                setDice(dice3, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice3)
            }
        }

        when (randomInt3) {
            1 -> {
                setDice(dice7, R.drawable.dice1)
                animate(dice7)
            }
            2 -> {
                setDice(dice7, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice7)
            }
            3 -> {
                setDice(dice7, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice7)
            }
            4 -> {
                setDice(dice7, R.drawable.dice4)
                animate(dice7)
            }
            5 -> {
                setDice(dice7, R.drawable.dice5)
                animate(dice7)
            }
            6 -> {
                setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice7)
            }
        }

        when (randomInt4) {
            1 -> {
                setDice(dice9, R.drawable.dice1)
                animate(dice9)
            }
            2 -> {
                setDice(dice9, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice9)
            }
            3 -> {
                setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice9)
            }
            4 -> {
                setDice(dice9, R.drawable.dice4)
                animate(dice9)
            }
            5 -> {
                setDice(dice9, R.drawable.dice5)
                animate(dice9)
            }
            6 -> {
                setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice9)
            }
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
            1 -> {
                setDice(dice1, R.drawable.dice1)
                animate(dice1)
            }
            2 -> {
                setDice(dice1, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice1)
            }
            3 -> {
                setDice(dice1, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice1)
            }
            4 -> {
                setDice(dice1, R.drawable.dice4)
                animate(dice1)
            }
            5 -> {
                setDice(dice1, R.drawable.dice5)
                animate(dice1)
            }
            6 -> {
                setDice(dice1, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice1)
            }
        }

        when (randomInt2) {
            1 -> {
                setDice(dice3, R.drawable.dice1)
                animate(dice3)
            }
            2 -> {
                setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice3)
            }
            3 -> {
                setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice3)
            }
            4 -> {
                setDice(dice3, R.drawable.dice4)
                animate(dice3)
            }
            5 -> {
                setDice(dice3, R.drawable.dice5)
                animate(dice3)
            }
            6 -> {
                setDice(dice3, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice3)
            }
        }

        when (randomInt3) {
            1 -> {
                setDice(dice5, R.drawable.dice1)
                animate(dice5)
            }
            2 -> {
                setDice(dice5, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice5)
            }
            3 -> {
                setDice(dice5, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice5)
            }
            4 -> {
                setDice(dice5, R.drawable.dice4)
                animate(dice5)
            }
            5 -> {
                setDice(dice5, R.drawable.dice5)
                animate(dice5)
            }
            6 -> {
                setDice(dice5, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice5)
            }
        }

        when (randomInt4) {
            1 -> {
                setDice(dice7, R.drawable.dice1)
                animate(dice7)
            }
            2 -> {
                setDice(dice7, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice7)
            }
            3 -> {
                setDice(dice7, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice7)
            }
            4 -> {
                setDice(dice7, R.drawable.dice4)
                animate(dice7)
            }
            5 -> {
                setDice(dice7, R.drawable.dice5)
                animate(dice7)
            }
            6 -> {
                setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice7)
            }
        }

        when (randomInt5) {
            1 -> {
                setDice(dice9, R.drawable.dice1)
                animate(dice9)
            }
            2 -> {
                setDice(dice9, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice9)
            }
            3 -> {
                setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice9)
            }
            4 -> {
                setDice(dice9, R.drawable.dice4)
                animate(dice9)
            }
            5 -> {
                setDice(dice9, R.drawable.dice5)
                animate(dice9)
            }
            6 -> {
                setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice9)
            }
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
            1 -> {
                setDice(dice1, R.drawable.dice1)
                animate(dice1)
            }
            2 -> {
                setDice(dice1, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice1)
            }
            3 -> {
                setDice(dice1, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice1)
            }
            4 -> {
                setDice(dice1, R.drawable.dice4)
                animate(dice1)
            }
            5 -> {
                setDice(dice1, R.drawable.dice5)
                animate(dice1)
            }
            6 -> {
                setDice(dice1, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice1)
            }
        }

        when (randomInt2) {
            1 -> {
                setDice(dice3, R.drawable.dice1)
                animate(dice3)
            }
            2 -> {
                setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice3)
            }
            3 -> {
                setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice3)
            }
            4 -> {
                setDice(dice3, R.drawable.dice4)
                animate(dice3)
            }
            5 -> {
                setDice(dice3, R.drawable.dice5)
                animate(dice3)
            }
            6 -> {
                setDice(dice3, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice3)
            }
        }

        when (randomInt3) {
            1 -> {
                setDice(dice4, R.drawable.dice1)
                animate(dice4)
            }
            2 -> {
                setDice(dice4, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice4)
            }
            3 -> {
                setDice(dice4, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice4)
            }
            4 -> {
                setDice(dice4, R.drawable.dice4)
                animate(dice4)
            }
            5 -> {
                setDice(dice4, R.drawable.dice5)
                animate(dice4)
            }
            6 -> {
                setDice(dice4, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice4)
            }
        }

        when (randomInt4) {
            1 -> {
                setDice(dice6, R.drawable.dice1)
                animate(dice6)
            }
            2 -> {
                setDice(dice6, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice6)
            }
            3 -> {
                setDice(dice6, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice6)
            }
            4 -> {
                setDice(dice6, R.drawable.dice4)
                animate(dice6)
            }
            5 -> {
                setDice(dice6, R.drawable.dice5)
                animate(dice6)
            }
            6 -> {
                setDice(dice6, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice6)
            }
        }

        when (randomInt5) {
            1 -> {
                setDice(dice7, R.drawable.dice1)
                animate(dice7)
            }
            2 -> {
                setDice(dice7, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice7)
            }
            3 -> {
                setDice(dice7, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice7)
            }
            4 -> {
                setDice(dice7, R.drawable.dice4)
                animate(dice7)
            }
            5 -> {
                setDice(dice7, R.drawable.dice5)
                animate(dice7)
            }
            6 -> {
                setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice7)
            }
        }

        when (randomInt6) {
            1 -> {
                setDice(dice9, R.drawable.dice1)
                animate(dice9)
            }
            2 -> {
                setDice(dice9, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice9)
            }
            3 -> {
                setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice9)
            }
            4 -> {
                setDice(dice9, R.drawable.dice4)
                animate(dice9)
            }
            5 -> {
                setDice(dice9, R.drawable.dice5)
                animate(dice9)
            }
            6 -> {
                setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice9)
            }
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
            1 -> {
                setDice(dice1, R.drawable.dice1)
                animate(dice1)
            }
            2 -> {
                setDice(dice1, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice1)
            }
            3 -> {
                setDice(dice1, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice1)
            }
            4 -> {
                setDice(dice1, R.drawable.dice4)
                animate(dice1)
            }
            5 -> {
                setDice(dice1, R.drawable.dice5)
                animate(dice1)
            }
            6 -> {
                setDice(dice1, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice1)
            }
        }

        when (randomInt2) {
            1 -> {
                setDice(dice3, R.drawable.dice1)
                animate(dice3)
            }
            2 -> {
                setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice3)
            }
            3 -> {
                setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice3)
            }
            4 -> {
                setDice(dice3, R.drawable.dice4)
                animate(dice3)
            }
            5 -> {
                setDice(dice3, R.drawable.dice5)
                animate(dice3)
            }
            6 -> {
                setDice(dice3, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice3)
            }
        }

        when (randomInt3) {
            1 -> {
                setDice(dice4, R.drawable.dice1)
                animate(dice4)
            }
            2 -> {
                setDice(dice4, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice4)
            }
            3 -> {
                setDice(dice4, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice4)
            }
            4 -> {
                setDice(dice4, R.drawable.dice4)
                animate(dice4)
            }
            5 -> {
                setDice(dice4, R.drawable.dice5)
                animate(dice4)
            }
            6 -> {
                setDice(dice4, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice4)
            }
        }

        when (randomInt4) {
            1 -> {
                setDice(dice5, R.drawable.dice1)
                animate(dice5)
            }
            2 -> {
                setDice(dice5, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice5)
            }
            3 -> {
                setDice(dice5, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice5)
            }
            4 -> {
                setDice(dice5, R.drawable.dice4)
                animate(dice5)
            }
            5 -> {
                setDice(dice5, R.drawable.dice5)
                animate(dice5)
            }
            6 -> {
                setDice(dice5, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice5)
            }
        }

        when (randomInt5) {
            1 -> {
                setDice(dice6, R.drawable.dice1)
                animate(dice6)
            }
            2 -> {
                setDice(dice6, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice6)
            }
            3 -> {
                setDice(dice6, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice6)
            }
            4 -> {
                setDice(dice6, R.drawable.dice4)
                animate(dice6)
            }
            5 -> {
                setDice(dice6, R.drawable.dice5)
                animate(dice6)
            }
            6 -> {
                setDice(dice6, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice6)
            }
        }

        when (randomInt6) {
            1 -> {
                setDice(dice7, R.drawable.dice1)
                animate(dice7)
            }
            2 -> {
                setDice(dice7, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice7)
            }
            3 -> {
                setDice(dice7, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice7)
            }
            4 -> {
                setDice(dice7, R.drawable.dice4)
                animate(dice7)
            }
            5 -> {
                setDice(dice7, R.drawable.dice5)
                animate(dice7)
            }
            6 -> {
                setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice7)
            }
        }

        when (randomInt7) {
            1 -> {
                setDice(dice9, R.drawable.dice1)
                animate(dice9)
            }
            2 -> {
                setDice(dice9, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice9)
            }
            3 -> {
                setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice9)
            }
            4 -> {
                setDice(dice9, R.drawable.dice4)
                animate(dice9)
            }
            5 -> {
                setDice(dice9, R.drawable.dice5)
                animate(dice9)
            }
            6 -> {
                setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice9)
            }
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
            1 -> {
                setDice(dice1, R.drawable.dice1)
                animate(dice1)
            }
            2 -> {
                setDice(dice1, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice1)
            }
            3 -> {
                setDice(dice1, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice1)
            }
            4 -> {
                setDice(dice1, R.drawable.dice4)
                animate(dice1)
            }
            5 -> {
                setDice(dice1, R.drawable.dice5)
                animate(dice1)
            }
            6 -> {
                setDice(dice1, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice1)
            }
        }

        when (randomInt2) {
            1 -> {
                setDice(dice2, R.drawable.dice1)
                animate(dice2)
            }
            2 -> {
                setDice(dice2, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice2)
            }
            3 -> {
                setDice(dice2, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice2)
            }
            4 -> {
                setDice(dice2, R.drawable.dice4)
                animate(dice2)
            }
            5 -> {
                setDice(dice2, R.drawable.dice5)
                animate(dice2)
            }
            6 -> {
                setDice(dice2, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice2)
            }
        }

        when (randomInt3) {
            1 -> {
                setDice(dice3, R.drawable.dice1)
                animate(dice3)
            }
            2 -> {
                setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice3)
            }
            3 -> {
                setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice3)
            }
            4 -> {
                setDice(dice3, R.drawable.dice4)
                animate(dice3)
            }
            5 -> {
                setDice(dice3, R.drawable.dice5)
                animate(dice3)
            }
            6 -> {
                setDice(dice3, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice3)
            }
        }

        when (randomInt4) {
            1 -> {
                setDice(dice4, R.drawable.dice1)
                animate(dice4)
            }
            2 -> {
                setDice(dice4, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice4)
            }
            3 -> {
                setDice(dice4, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice4)
            }
            4 -> {
                setDice(dice4, R.drawable.dice4)
                animate(dice4)
            }
            5 -> {
                setDice(dice4, R.drawable.dice5)
                animate(dice4)
            }
            6 -> {
                setDice(dice4, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice4)
            }
        }

        when (randomInt5) {
            1 -> {
                setDice(dice6, R.drawable.dice1)
                animate(dice6)
            }
            2 -> {
                setDice(dice6, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice6)
            }
            3 -> {
                setDice(dice6, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice6)
            }
            4 -> {
                setDice(dice6, R.drawable.dice4)
                animate(dice6)
            }
            5 -> {
                setDice(dice6, R.drawable.dice5)
                animate(dice6)
            }
            6 -> {
                setDice(dice6, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice6)
            }
        }

        when (randomInt6) {
            1 -> {
                setDice(dice7, R.drawable.dice1)
                animate(dice7)
            }
            2 -> {
                setDice(dice7, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice7)
            }
            3 -> {
                setDice(dice7, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice7)
            }
            4 -> {
                setDice(dice7, R.drawable.dice4)
                animate(dice7)
            }
            5 -> {
                setDice(dice7, R.drawable.dice5)
                animate(dice7)
            }
            6 -> {
                setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice7)
            }
        }

        when (randomInt7) {
            1 -> {
                setDice(dice8, R.drawable.dice1)
                animate(dice8)
            }
            2 -> {
                setDice(dice8, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice8)
            }
            3 -> {
                setDice(dice8, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice8)
            }
            4 -> {
                setDice(dice8, R.drawable.dice4)
                animate(dice8)
            }
            5 -> {
                setDice(dice8, R.drawable.dice5)
                animate(dice8)
            }
            6 -> {
                setDice(dice8, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice8)
            }
        }

        when (randomInt8) {
            1 -> {
                setDice(dice9, R.drawable.dice1)
                animate(dice9)
            }
            2 -> {
                setDice(dice9, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice9)
            }
            3 -> {
                setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice9)
            }
            4 -> {
                setDice(dice9, R.drawable.dice4)
                animate(dice9)
            }
            5 -> {
                setDice(dice9, R.drawable.dice5)
                animate(dice9)
            }
            6 -> {
                setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice9)
            }
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
            1 -> {
                setDice(dice1, R.drawable.dice1)
                animate(dice1)
            }
            2 -> {
                setDice(dice1, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice1)
            }
            3 -> {
                setDice(dice1, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice1)
            }
            4 -> {
                setDice(dice1, R.drawable.dice4)
                animate(dice1)
            }
            5 -> {
                setDice(dice1, R.drawable.dice5)
                animate(dice1)
            }
            6 -> {
                setDice(dice1, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice1)
            }
        }

        when (randomInt2) {
            1 -> {
                setDice(dice2, R.drawable.dice1)
                animate(dice2)
            }
            2 -> {
                setDice(dice2, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice2)
            }
            3 -> {
                setDice(dice2, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice2)
            }
            4 -> {
                setDice(dice2, R.drawable.dice4)
                animate(dice2)
            }
            5 -> {
                setDice(dice2, R.drawable.dice5)
                animate(dice2)
            }
            6 -> {
                setDice(dice2, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice2)
            }
        }

        when (randomInt3) {
            1 -> {
                setDice(dice3, R.drawable.dice1)
                animate(dice3)
            }
            2 -> {
                setDice(dice3, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice3)
            }
            3 -> {
                setDice(dice3, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice3)
            }
            4 -> {
                setDice(dice3, R.drawable.dice4)
                animate(dice3)
            }
            5 -> {
                setDice(dice3, R.drawable.dice5)
                animate(dice3)
            }
            6 -> {
                setDice(dice3, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice3)
            }
        }

        when (randomInt4) {
            1 -> {
                setDice(dice4, R.drawable.dice1)
                animate(dice4)
            }
            2 -> {
                setDice(dice4, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice4)
            }
            3 -> {
                setDice(dice4, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice4)
            }
            4 -> {
                setDice(dice4, R.drawable.dice4)
                animate(dice4)
            }
            5 -> {
                setDice(dice4, R.drawable.dice5)
                animate(dice4)
            }
            6 -> {
                setDice(dice4, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice4)
            }
        }

        when (randomInt5) {
            1 -> {
                setDice(dice5, R.drawable.dice1)
                animate(dice5)
            }
            2 -> {
                setDice(dice5, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice5)
            }
            3 -> {
                setDice(dice5, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice5)
            }
            4 -> {
                setDice(dice5, R.drawable.dice4)
                animate(dice5)
            }
            5 -> {
                setDice(dice5, R.drawable.dice5)
                animate(dice5)
            }
            6 -> {
                setDice(dice5, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice5)
            }
        }

        when (randomInt6) {
            1 -> {
                setDice(dice6, R.drawable.dice1)
                animate(dice6)
            }
            2 -> {
                setDice(dice6, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice6)
            }
            3 -> {
                setDice(dice6, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice6)
            }
            4 -> {
                setDice(dice6, R.drawable.dice4)
                animate(dice6)
            }
            5 -> {
                setDice(dice6, R.drawable.dice5)
                animate(dice6)
            }
            6 -> {
                setDice(dice6, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice6)
            }
        }

        when (randomInt7) {
            1 -> {
                setDice(dice7, R.drawable.dice1)
                animate(dice7)
            }
            2 -> {
                setDice(dice7, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice7)
            }
            3 -> {
                setDice(dice7, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice7)
            }
            4 -> {
                setDice(dice7, R.drawable.dice4)
                animate(dice7)
            }
            5 -> {
                setDice(dice7, R.drawable.dice5)
                animate(dice7)
            }
            6 -> {
                setDice(dice7, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice7)
            }
        }

        when (randomInt8) {
            1 -> {
                setDice(dice8, R.drawable.dice1)
                animate(dice8)
            }
            2 -> {
                setDice(dice8, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice8)
            }
            3 -> {
                setDice(dice8, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice8)
            }
            4 -> {
                setDice(dice8, R.drawable.dice4)
                animate(dice8)
            }
            5 -> {
                setDice(dice8, R.drawable.dice5)
                animate(dice8)
            }
            6 -> {
                setDice(dice8, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice8)
            }
        }

        when (randomInt9) {
            1 -> {
                setDice(dice9, R.drawable.dice1)
                animate(dice9)
            }
            2 -> {
                setDice(dice9, arrayOf(R.drawable.dice2_1, R.drawable.dice2_2).random())
                animate(dice9)
            }
            3 -> {
                setDice(dice9, arrayOf(R.drawable.dice3_1, R.drawable.dice3_2).random())
                animate(dice9)
            }
            4 -> {
                setDice(dice9, R.drawable.dice4)
                animate(dice9)
            }
            5 -> {
                setDice(dice9, R.drawable.dice5)
                animate(dice9)
            }
            6 -> {
                setDice(dice9, arrayOf(R.drawable.dice6_1, R.drawable.dice6_2).random())
                animate(dice9)
            }
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

    private fun animate(view: ImageView) {
        val anim = RotateAnimation(
            0f, 359f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        anim.duration = 150
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                rollButton.isClickable = false
            }

            override fun onAnimationEnd(animation: Animation) {
                rollButton.isClickable = true
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        view.animation = anim
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(Constants.AD_COUNTER, Constants.adCounter)
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
        Constants.adCounter = sharedPreferences.getInt(Constants.AD_COUNTER, 0)
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }

    override fun onBackPressed() {
        counter++
        if (counter==1) {
            saveData()
            Toasty.custom(this, R.string.toast, R.drawable.ic_exit, R.color.toast,
                Toast.LENGTH_LONG,true, true).show()
        } else {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}