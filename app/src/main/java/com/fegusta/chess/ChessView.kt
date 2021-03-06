package com.fegusta.chess

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private final val originX: Float = 10f
    private final val originY: Float = 200f
    private final val cellSide: Float = 130f
    private final val darkColor = Color.argb(1f,.3f,.3f,.3f)
    private final val lighColor = Color.argb(1f,.7f,.7f,.7f)
    private final val imgResIds = setOf(
            R.drawable.bishop_black,
            R.drawable.bishop_white,
            R.drawable.king_black,
            R.drawable.king_white,
            R.drawable.queen_black,
            R.drawable.queen_white,
            R.drawable.rook_black,
            R.drawable.rook_white,
            R.drawable.knight_black,
            R.drawable.knight_white,
            R.drawable.pawn_black,
            R.drawable.pawn_white
    )
    private final val bitmaps = mutableMapOf<Int, Bitmap>()
    private final val paint = Paint()

    var chessDelegate: ChessDelegate? = null

    init {
        loadBitmaps()
    }

    override fun onDraw(canvas: Canvas?) {
        drawChessboard(canvas)
        drawPieces(canvas)
    }

    private  fun drawPieces(canvas: Canvas?) {
        for (row in 0..7) {
            for (col in 0..7){
                chessDelegate?.pieceAt(col, row)?.let { drawPieceAt(canvas, col, row, it.resID) }
            }
        }
    }

    private fun drawPieceAt(canvas: Canvas?, col: Int, row: Int, resID: Int) {
        val bitmap = bitmaps[resID]!!
        canvas?.drawBitmap(bitmap, null, RectF(originX + col * cellSide, originY + (7 - row) * cellSide, originX + (col + 1) * cellSide, originY + ((7 - row) + 1) * cellSide), paint)
    }

    private fun loadBitmaps() {
        imgResIds.forEach {
            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
        }
    }

    private fun drawChessboard(canvas: Canvas?) {
        for (row in 0..7) {
            for (col in 0..7){
                drawSquareAt(canvas, col, row, (col + row) % 2 == 1)
            }
        }
    }

    private fun drawSquareAt(canvas: Canvas?, col: Int, row: Int, isDark: Boolean) {
        paint.color = if (isDark) darkColor else lighColor
        canvas?.drawRect(originX + col * cellSide, originY + row * cellSide, originX + (col + 1) * cellSide, originY + (row + 1) * cellSide, paint)
    }
}