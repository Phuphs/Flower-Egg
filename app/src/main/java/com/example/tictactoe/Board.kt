package com.example.tictactoe

class Board(private val gameBoard:MutableMap<Cell,CellState> = mutableMapOf()) {
    fun getState(cell:Cell):CellState{
        return gameBoard[cell]?:CellState.Empty
    }

    fun setCell(cell: Cell,state: CellState):Boolean{
        if(gameBoard.containsKey(cell)){
            return false
        }
        gameBoard[cell]=state
        return true

    }

    fun resetBoard(){
        gameBoard.clear()
    }
    fun findNextWinningMove(state:CellState):Cell?=when{
        Cell.TOP_LEFT winsFor state -> Cell.TOP_LEFT
        Cell.TOP_CENTER winsFor state -> Cell.TOP_CENTER
        Cell.TOP_RIGHT winsFor state -> Cell.TOP_RIGHT
        Cell.CENTER_LEFT winsFor state -> Cell.CENTER_LEFT
        Cell.CENTER_CENTER winsFor state -> Cell.CENTER_CENTER
        Cell.CENTER_RIGHT winsFor state -> Cell.CENTER_RIGHT
        Cell.BOTTOM_LEFT winsFor state -> Cell.BOTTOM_LEFT
        Cell.BOTTOM_CENTER winsFor state -> Cell.BOTTOM_CENTER
        Cell.BOTTOM_RIGHT winsFor state -> Cell.BOTTOM_RIGHT


        else->null
    }
    private infix fun Cell.winsFor(state: CellState):Boolean{
        if(gameBoard.containsKey(this)){
            return false
        }
        gameBoard[this]=state
        val hasWon=hasStateWon(state)
        gameBoard.remove(this)
        return hasWon
    }

    val boardState : BoardState
    get()= when{
        hasStateWon(CellState.Egg)->BoardState.EGG_WINS
        hasStateWon(CellState.Flower)->BoardState.FLOWER_WINS
        gameBoard.size<9->BoardState.INCOMPLETE
        else->BoardState.DRAW
    }
    private fun hasStateWon(state: CellState):Boolean{
     fun testState(vararg cells:Cell ):Boolean = cells.all {
         cell-> gameBoard[cell]==state
     }
        return testState(Cell.TOP_LEFT,Cell.CENTER_LEFT,Cell.BOTTOM_LEFT)||
                testState(Cell.TOP_CENTER,Cell.CENTER_CENTER,Cell.BOTTOM_CENTER)||
                testState(Cell.TOP_RIGHT,Cell.CENTER_RIGHT,Cell.BOTTOM_RIGHT)||
                testState(Cell.TOP_LEFT,Cell.TOP_CENTER,Cell.TOP_RIGHT)||
                testState(Cell.BOTTOM_LEFT,Cell.BOTTOM_CENTER,Cell.BOTTOM_RIGHT)||
                testState(Cell.CENTER_LEFT,Cell.CENTER_CENTER,Cell.CENTER_RIGHT)||
                testState(Cell.TOP_LEFT,Cell.CENTER_CENTER,Cell.BOTTOM_RIGHT)||
                testState(Cell.BOTTOM_LEFT,Cell.CENTER_CENTER,Cell.TOP_RIGHT)
    }
}