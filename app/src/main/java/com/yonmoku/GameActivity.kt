package com.yonmoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class GameActivity : AppCompatActivity() {
    var actPlayer = 1                           //  操作ブレイヤー

    var selectedListPLayer1 = ArrayList<Int>()  //  プレイヤー1が置いた場所リスト
    var selectedListPLayer2 = ArrayList<Int>()  //  プレイヤー2が置いた場所リスト

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        
        val resetBtn = findViewById<Button>(R.id.resetButton)
        resetBtn.setOnClickListener {
            resetGame()
        }
    }
    
    
    fun clickBtn(view: View) {
        val selectBtn = view as ImageButton
        
        var btnId = 0
        when (selectBtn.id) {
            R.id.imageButton1A -> btnId = 1
            R.id.imageButton1B -> btnId = 2
            R.id.imageButton1C -> btnId = 3
            R.id.imageButton1D -> btnId = 4
            R.id.imageButton2A -> btnId = 5
            R.id.imageButton2B -> btnId = 6
            R.id.imageButton2C -> btnId = 7
            R.id.imageButton2D -> btnId = 8
            R.id.imageButton3A -> btnId = 9
            R.id.imageButton3B -> btnId = 10
            R.id.imageButton3C -> btnId = 11
            R.id.imageButton3D -> btnId = 12
            R.id.imageButton4A -> btnId = 13
            R.id.imageButton4B -> btnId = 14
            R.id.imageButton4C -> btnId = 15
            R.id.imageButton4D -> btnId = 16
        }

        playGame(btnId, selectBtn)
    }

    private fun playGame(btnId: Int, selectBtn: ImageButton) {
        if (actPlayer == 1) {
            when((1..3).random()) {
                1 -> selectBtn.setImageResource(R.drawable.kinoko1)
                2 -> selectBtn.setImageResource(R.drawable.kinoko2)
                3 -> selectBtn.setImageResource(R.drawable.kinoko3)
            }
            selectedListPLayer1.add(btnId)
            actPlayer = 2
        } else {
            when((1..3).random()) {
                1 -> selectBtn.setImageResource(R.drawable.takenoko1)
                2 -> selectBtn.setImageResource(R.drawable.takenoko2)
                3 -> selectBtn.setImageResource(R.drawable.takenoko3)
            }
            selectedListPLayer2.add(btnId)
            actPlayer = 1
        }

        selectBtn.isEnabled = false
        checkWinner()
    }

    private fun checkWinner() {
        var winner = -1

        if (
            // 横1列
            selectedListPLayer1.containsAll(listOf(1, 2, 3, 4)) ||
            selectedListPLayer1.containsAll(listOf(5, 6, 7, 8)) ||
            selectedListPLayer1.containsAll(listOf(9, 10, 11, 12)) ||
            selectedListPLayer1.containsAll(listOf(13, 14, 15, 16)) ||
            // 縦1列
            selectedListPLayer1.containsAll(listOf(1, 5, 9, 13)) ||
            selectedListPLayer1.containsAll(listOf(2, 6, 10, 14)) ||
            selectedListPLayer1.containsAll(listOf(3, 7, 11, 15)) ||
            selectedListPLayer1.containsAll(listOf(4, 8, 12, 16)) ||
            // 斜め
            selectedListPLayer1.containsAll(listOf(1, 6, 11, 16)) ||
            selectedListPLayer1.containsAll(listOf(4, 7, 10, 13))

        ) {
            winner = 1
        }

        if (
            // 横1列
            selectedListPLayer2.containsAll(listOf(1, 2, 3, 4)) ||
            selectedListPLayer2.containsAll(listOf(5, 6, 7, 8)) ||
            selectedListPLayer2.containsAll(listOf(9, 10, 11, 12)) ||
            selectedListPLayer2.containsAll(listOf(13, 14, 15, 16)) ||
            // 縦1列
            selectedListPLayer2.containsAll(listOf(1, 5, 9, 13)) ||
            selectedListPLayer2.containsAll(listOf(2, 6, 10, 14)) ||
            selectedListPLayer2.containsAll(listOf(3, 7, 11, 15)) ||
            selectedListPLayer2.containsAll(listOf(4, 8, 12, 16)) ||
            // 斜め
            selectedListPLayer2.containsAll(listOf(1, 6, 11, 16)) ||
            selectedListPLayer2.containsAll(listOf(4, 7, 10, 13))

        ) {
            winner = 2
        }

        if (winner == 1) {
            Toast.makeText(this, "先攻の勝利！", Toast.LENGTH_LONG).show()
            findViewById<Button>(R.id.resetButton).visibility = View.VISIBLE
        } else if (winner == 2) {
            Toast.makeText(this, "後攻の勝利！", Toast.LENGTH_LONG).show()
            findViewById<Button>(R.id.resetButton).visibility = View.VISIBLE
        } else if ((selectedListPLayer1.size + selectedListPLayer2.size) == 16) {
            Toast.makeText(this, "ドロー！", Toast.LENGTH_LONG).show()
            findViewById<Button>(R.id.resetButton).visibility = View.VISIBLE
        }
    }

    private fun resetGame() {
        actPlayer = 1
        selectedListPLayer1.clear()
        selectedListPLayer2.clear()
        findViewById<Button>(R.id.resetButton).visibility = View.INVISIBLE

        for (index in 1..16) {
            val selectBtn: ImageButton = when(index) {
                1 -> findViewById(R.id.imageButton1A)
                2 -> findViewById(R.id.imageButton1B)
                3 -> findViewById(R.id.imageButton1C)
                4 -> findViewById(R.id.imageButton1D)
                5 -> findViewById(R.id.imageButton2A)
                6 -> findViewById(R.id.imageButton2B)
                7 -> findViewById(R.id.imageButton2C)
                8 -> findViewById(R.id.imageButton2D)
                9 -> findViewById(R.id.imageButton3A)
                10 -> findViewById(R.id.imageButton3B)
                11 -> findViewById(R.id.imageButton3C)
                12 -> findViewById(R.id.imageButton3D)
                13 -> findViewById(R.id.imageButton4A)
                14 -> findViewById(R.id.imageButton4B)
                15 -> findViewById(R.id.imageButton4C)
                16 -> findViewById(R.id.imageButton4D)
                else -> {findViewById(R.id.imageButton1A)}
            }
            selectBtn.setBackgroundResource(R.drawable.border)
            selectBtn.setImageResource(R.drawable.border)
            selectBtn.isEnabled = true
        }
    }
}