package com.fegusta.chess

interface ChessDelegate {
    fun pieceAt(col: Int, row: Int) : ChessPiece?
}