package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    val vm:MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm.liveBoard.observe(this,onBoardChange)

        bindButtons()
    }
    private val onBoardChange= Observer{
        board:Board->
        updateBoardCell(board)
        updateGameStatus(board.boardState)
    }

    private fun bindButtons() =with(binding){
        resetbtn.setOnClickListener{vm.resetBoard()}
        cell1.setOnClickListener{vm.cellClicked(Cell.TOP_LEFT)}
        cell2.setOnClickListener{vm.cellClicked(Cell.TOP_CENTER)}
        cell3.setOnClickListener{vm.cellClicked(Cell.TOP_RIGHT)}
        cell4.setOnClickListener{vm.cellClicked(Cell.CENTER_LEFT)}
        cell5.setOnClickListener{vm.cellClicked(Cell.CENTER_CENTER)}
        cell6.setOnClickListener{vm.cellClicked(Cell.CENTER_RIGHT)}
        cell7.setOnClickListener{vm.cellClicked(Cell.BOTTOM_LEFT)}
        cell8.setOnClickListener{vm.cellClicked(Cell.BOTTOM_CENTER)}
        cell9.setOnClickListener{vm.cellClicked(Cell.BOTTOM_RIGHT)}

    }

    private fun updateBoardCell(board: Board){
        binding.cell1.setImageResource(board.getState(Cell.TOP_LEFT).res)
        binding.cell2.setImageResource(board.getState(Cell.TOP_CENTER).res)
        binding.cell3.setImageResource(board.getState(Cell.TOP_RIGHT).res)
        binding.cell4.setImageResource(board.getState(Cell.CENTER_LEFT).res)
        binding.cell5.setImageResource(board.getState(Cell.CENTER_CENTER).res)
        binding.cell6.setImageResource(board.getState(Cell.CENTER_RIGHT).res)
        binding.cell7.setImageResource(board.getState(Cell.BOTTOM_LEFT).res)
        binding.cell8.setImageResource(board.getState(Cell.BOTTOM_CENTER).res)
        binding.cell9.setImageResource(board.getState(Cell.BOTTOM_RIGHT).res)


    }
    private fun updateGameStatus(boardState: BoardState)=when(boardState){
        BoardState.EGG_WINS->{
            binding.gamestatus.visibility= View.VISIBLE
            binding.gamestatus.setText(R.string.egg_wins)
        }
    BoardState.FLOWER_WINS->{
        binding.gamestatus.visibility= View.VISIBLE
        binding.gamestatus.setText(R.string.flower_wins)
    }
            BoardState.DRAW->{
            binding.gamestatus.visibility= View.VISIBLE
            binding.gamestatus.setText(R.string.draw)
            }
            BoardState.INCOMPLETE->{
            binding.gamestatus.visibility= View.GONE
            binding.gamestatus.text="The Game is Ready To Play"
            }
    }
}