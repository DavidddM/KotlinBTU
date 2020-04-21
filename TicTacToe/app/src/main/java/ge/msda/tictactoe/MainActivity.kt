package ge.msda.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var grid: Array<Array<Int>> = Array(3) { Array(3) { 0 } }
    private val playerMap: Map<Int, String> = mapOf(1 to "X", 2 to "O")
    private val playerScoreMap: MutableMap<Int, Int> = mutableMapOf(1 to 0, 2 to 0)

    private lateinit var playButtons: List<Button>
    private lateinit var playerScoreViews: Map<Int, TextView>

    private var activePlayer = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null && savedInstanceState.containsKey("flatGrid")) {
            val flatGrid = savedInstanceState.getIntArray("flatGrid")!!
            var ind = 0
            for(i in 0..2) {
                for(j in 0..2) {
                    this.grid[i][j] = flatGrid[ind]
                    ind++
                }
            }

        }
        this.init()
    }

    private fun init() {
        playButtons =
            listOf(button1, button2, button3, button4, button5, button6, button7, button8, button9)
        playerScoreViews = mapOf(1 to xScoreTView, 2 to oScoreTView)

        for ((index, elem) in this.grid.flatten().withIndex()) {
            if (elem != 0) {
                val currentButton =
                    playButtons.firstOrNull { button ->
                        button.tag.toString().toInt() == (index + 1)
                    }
                if (currentButton is Button) {
                    currentButton.text = playerMap[elem]
                    currentButton.isEnabled = false
                }
            }
        }

        renderScore(1)
        renderScore(2)

        playButtons.forEach { it.setOnClickListener(this) }

        resetButton.setOnClickListener {
            playButtons.forEach {
                it.text = ""
                it.isEnabled = true
            }
            this.grid = Array(3) { Array(3) { 0 } }
            this.activePlayer = 1
        }
    }

    private fun renderScore(player: Int) {
        (playerScoreViews[player] ?: error("Views not initialized!")).text =
            playerMap[player] + " - " + playerScoreMap[player].toString()
    }

    private fun nextPlayer() {
        when (this.activePlayer) {
            1 -> this.activePlayer = 2
            2 -> this.activePlayer = 1
        }
    }

    private fun getPosition(clickedView: View): Int {
        return clickedView.tag.toString().toInt()
    }

    private fun freeze() {
        playButtons.forEach {
            it.isEnabled = false
        }
    }

    override fun onClick(clickedView: View?) {

        if (clickedView is Button) {

            val pos = this.getPosition(clickedView)

            if (pos != 0) {
                val j = (pos - 1) % 3
                val i = ((pos - 1) / 3)
                this.grid[i][j] = this.activePlayer
                clickedView.isEnabled = false
                clickedView.text = this.playerMap[this.activePlayer]
                this.CheckForWin(i, j, i == j || abs(i - j) == 2, this.activePlayer)
                this.nextPlayer()
            }
        }
    }

    private fun CheckForWin(x: Int, y: Int, checkForDiagonal: Boolean, activeP: Int) {
        var winX = true
        var winY = true
        var winDiag1 = false
        var winDiag2 = false

        var tie = true

        for (i in 0..2) {
            for (j in 0..2) {
                if (this.grid[i][j] == 0) tie = false
            }
        }

        for (i in 0..1) {
            if (grid[x][i] != grid[x][i + 1]) winX = false
            if (grid[i][y] != grid[i + 1][y]) winY = false
        }
        if (checkForDiagonal) {
            if (x == y) {
                winDiag1 = true
                for (i in 0..1) {
                    if (grid[i][i] != grid[i + 1][i + 1]) winDiag1 = false
                }
            } else {
                winDiag2 = true
                // ეჰ, multivariable for loop :(
                var i = 0
                var j = 2
                while (i < 2 && j > 0) {
                    if (grid[i][j] != grid[i + 1][j - 1]) winDiag2 = false
                    i++
                    j--
                }
            }
        }

        if (winX || winY || winDiag1 || winDiag2) {
            Toast.makeText(
                applicationContext,
                this.playerMap[activeP] + " wins!",
                Toast.LENGTH_LONG
            ).show()
            this.playerScoreMap[activeP] = this.playerScoreMap[activeP]!! + 1
            this.renderScore(activeP)
            this.freeze()
        } else if (tie) {
            Toast.makeText(applicationContext, "Tie!", Toast.LENGTH_LONG).show()
            this.freeze()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("xScore", this.playerScoreMap[1]!!)
        outState.putInt("oScore", this.playerScoreMap[2]!!)
        outState.putInt("activePlayer", this.activePlayer)
        outState.putIntArray("flatGrid", this.grid.flatten().toIntArray())

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        this.playerScoreMap[1] = savedInstanceState.getInt("xScore")
        this.playerScoreMap[2] = savedInstanceState.getInt("oScore")
        this.activePlayer = savedInstanceState.getInt("activePlayer")

        this.renderScore(1)
        this.renderScore(2)

        super.onRestoreInstanceState(savedInstanceState)
    }
}