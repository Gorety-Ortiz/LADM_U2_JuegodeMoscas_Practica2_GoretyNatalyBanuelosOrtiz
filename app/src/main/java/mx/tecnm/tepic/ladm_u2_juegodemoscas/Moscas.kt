package mx.tecnm.tepic.ladm_u2_juegodemoscas

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

class Moscas (PLienzo: Lienzo, posX:Float, posY:Float, nombreImagen:Int)
{
    var im = BitmapFactory.decodeResource(PLienzo.resources, nombreImagen)
    var anchoX = posX
    var altoY = posY

    //VOLVER A PINTAR MOSCA MUERTA
    fun repintar(ancho:Int, alto:Int)
    {
        anchoX= ancho.toFloat()
        altoY= alto.toFloat()
    }
    fun pintarMosaca(c: Canvas)
    {
        c.drawBitmap(im,anchoX,altoY, Paint())
    }
    //HARIA DONDE SE MUEVEN MOSCAS Y DAS EL TOQUE
    fun AreaDeToque(toqueEnAncho:Float, toqueEnAlto:Float): Boolean{
        var x2= anchoX + im.width+50
        var y2 = altoY + im.height+50

        if(toqueEnAncho>= anchoX && toqueEnAncho<=x2)
        {
            if(toqueEnAlto>=altoY && toqueEnAlto<=y2)
            {
                return true
            }
        }
        return false
    }


}