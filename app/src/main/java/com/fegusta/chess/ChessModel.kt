package com.fegusta.chess

class ChessModel {

    var pieceBox = mutableSetOf<ChessPiece>()

    init {
        pieceBox.add(ChessPiece(0,0,ChessPlayer.WHITE, ChessRank.ROOK))
        pieceBox.add(ChessPiece(0,7,ChessPlayer.BLACK, ChessRank.ROOK))
    }

    fun pieceAt(col: Int, row: Int) : ChessPiece? {
        for (piece in pieceBox) {
            if (col == piece.col && row == piece.row) {
                return piece
            }
        }
        return null
    }

    override fun toString(): String {
        var desc = " \n"
        for (row in 7 downTo 0){
            desc += "$row"
            for (col in 0..7) {
                val piece = pieceAt(col, row)
                if(piece == null){
                    desc += " ."
                } else {
                    val white = piece.player == ChessPlayer.WHITE
                    desc += " "
                    when (piece.rank) {
                        ChessRank.KING -> { desc += if (white) "k" else "K" }
                        ChessRank.QUEEN -> { desc += if (white) "q" else "Q" }
                        ChessRank.BISHOP-> { desc += if (white) "b" else "B" }
                        ChessRank.ROOK -> { desc += if (white) "r" else "R" }
                        ChessRank.KNIGHT -> { desc += if (white) "n" else "N" }
                        ChessRank.PAWN -> { desc += if (white) "p" else "P" }
                    }
                }
            }
            desc += "\n"
        }
        desc += "  0 1 2 3 4 5 6 7"
        return desc
    }
}