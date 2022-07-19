package com.example.tictactoe

import androidx.annotation.DrawableRes

sealed class CellState (@DrawableRes val res:Int){
    object Egg:CellState(R.drawable.ic_egg)
    object Flower:CellState(R.drawable.ic_flower)
    object Empty:CellState(R.drawable.ic_empty)

}