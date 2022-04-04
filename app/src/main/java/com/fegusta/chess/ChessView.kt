package com.fegusta.chess

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private final val originX: Float = 10f
    private final val originY: Float = 200f
    private final val cellSide: Float = 130f
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

    init {
        loadBitmaps()
    }

    override fun onDraw(canvas: Canvas?) {
        drawChessboard(canvas)

        val whiteQueenBitmap = bitmaps[R.drawable.bishop_black]!!
        canvas?.drawBitmap(whiteQueenBitmap, null, Rect(0,0,600,600), paint)
    }

    private fun loadBitmaps() {
        imgResIds.forEach {
            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
        }
    }

    private fun drawChessboard(canvas: Canvas?) {
        for (j in 0..7) {
            for (i in 0..7){
                paint.color = if ((i + j) % 2 == 1) Color.DKGRAY else Color.LTGRAY
                canvas?.drawRect(originX + i * cellSide,originY + j * cellSide,originX + (i + 1) * cellSide, originY + (j + 1) * cellSide, paint)
            }
        }
    }
}