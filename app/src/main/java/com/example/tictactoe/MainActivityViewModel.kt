package com.example.tictactoe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private var board =Board()
    var liveBoard=MutableLiveData(board)

    private fun updateBoard(){
        liveBoard.value=board
    }
    fun cellClicked(cell: Cell){
        board.setCell(cell,CellState.Egg)
        updateBoard()
        if(board.boardState==BoardState.INCOMPLETE){
            aiTurn()
        }
    }
    fun resetBoard(){
        board.resetBoard()
        updateBoard()
    }

private fun aiTurn(){
    val aiWinnningCell=board.findNextWinningMove(CellState.Flower)
    val playerWinningCell=board.findNextWinningMove(CellState.Egg)
    when{
        aiWinnningCell != null->board.setCell(aiWinnningCell,CellState.Flower)
        playerWinningCell != null->board.setCell(playerWinningCell,CellState.Flower)
        board.setCell(Cell.CENTER_CENTER,CellState.Flower)->Unit
        else->do{
            val cell=Cell.values().random()
            val placedsuccessfully=board.setCell(cell,CellState.Flower)
        }while(!placedsuccessfully)
    }

    updateBoard()
}
}