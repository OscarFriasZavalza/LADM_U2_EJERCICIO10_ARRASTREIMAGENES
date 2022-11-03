package com.example.ladm_u2_ejercicio10_arrastreimagenes

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class Lienzo(p: MainActivity) : View(p) {
    val calavera = Figura(this,R.drawable.calavera,100f,200f)
    val calabaza = Figura(this,R.drawable.calabaza,600f,200f)
    val caldero = Figura(this,R.drawable.caldero,100f,800f)
    val bruja =Figura(this,R.drawable.bruja180,600f,800f)
    var punteroFigura:Figura?=null


    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val p = Paint()

        calavera.pintar(c)
        calabaza.pintar(c)
        caldero.pintar(c)
        bruja.pintar(c)


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if(bruja.determinarArea(event.x,event.y)){
                        punteroFigura=bruja
                    }
                    if(caldero.determinarArea(event.x,event.y)){
                        punteroFigura=caldero
                    }
                    if(calabaza.determinarArea(event.x,event.y)){
                        punteroFigura=calabaza
                    }
                    if(calavera.determinarArea(event.x,event.y)){
                        punteroFigura=calavera
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    if (punteroFigura!=null){
                        punteroFigura!!.mover(event.x,event.y)
                        if (punteroFigura==bruja){
                            if (bruja.colision(caldero)) caldero.invisible=true
                        }
                        if (punteroFigura==bruja){
                            if (bruja.colision(calabaza)) calabaza.invisible=true
                        }
                        if (punteroFigura==bruja){
                            if (bruja.colision(calavera)) calavera.invisible=true
                        }
                        if (punteroFigura==caldero){
                            if (bruja.colision(bruja)) bruja.invisible=true
                        }
                    }
                }
                MotionEvent.ACTION_UP -> {
                    punteroFigura=null
                }
            }
        }
        invalidate()
        return true
    }
}