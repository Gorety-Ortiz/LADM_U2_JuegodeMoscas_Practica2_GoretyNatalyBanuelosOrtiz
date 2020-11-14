package mx.tecnm.tepic.ladm_u2_juegodemoscas

import android.graphics.Canvas
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import java.lang.Exception
import kotlin.random.Random

class Lienzo (p:MainActivity): View(p){


    //VARIABLES
    var posicion = Random
    var moscax1= posicion.nextInt(1000).toFloat()
    var moscay1=posicion.nextInt(1500).toFloat()
    var moscax2=posicion.nextInt(1000).toFloat()
    var moscay2=posicion.nextInt(1500).toFloat()
    var moscax3=posicion.nextInt(1000).toFloat()
    var moscay3=posicion.nextInt(1500).toFloat()
    var moscax4=posicion.nextInt(1000).toFloat()
    var moscay4=posicion.nextInt(1500).toFloat()
    var moscax5=posicion.nextInt(1000).toFloat()
    var moscay5=posicion.nextInt(1500).toFloat()
    //LAS MOSCAS
    var mosca1 = Moscas(this,moscax1,moscay1,R.drawable.mosca)
    var mosca2 = Moscas(this,moscax2,moscay2,R.drawable.mosca)
    var mosca3= Moscas(this,moscax3,moscay3,R.drawable.mosca)
    var mosca4 = Moscas(this,moscax4,moscay4,R.drawable.mosca)
    var mosca5 = Moscas(this,moscax5,moscay5,R.drawable.mosca)

    // VARIABLES PARA PINTAR SANGRE DE MOSCAS
    var sangre1=Moscas(this,-1000f,-1000f,R.drawable.sangre)
    var sangre2=Moscas(this,-1000f,-1000f,R.drawable.sangre)
    var sangre3=Moscas(this,-1000f,-1000f,R.drawable.sangre)
    var sangre4=Moscas(this,-1000f,-1000f,R.drawable.sangre)
    var sangre5=Moscas(this,-1000f,-1000f,R.drawable.sangre)

    var inicio=false
    var puntos = 1 //PARA SUMAR PUNTOS DE UNO EN UNO
    var puntaje = "Muertes = "

    var BStart = Moscas(this,500f,700f,R.drawable.start)
    var p = Paint()
    var TRestante:Int=0
    var moscas = Random.nextInt(80,100)

    //VARIABLES DEL TIMER
    var CTimer=0 //CONTADOR PARA TIMER
    val TTOTAL=9999999
    val LAPSO=1000

    //MANDAR A LLAMAR CLASE HILO EXTERNA
    var HMosca1 = hilo(this,mosca1)
    var HMosca2 = hilo(this,mosca2)
    var HMosca3 = hilo(this,mosca3)
    var HMosca4 = hilo(this,mosca4)
    var HMosca5 = hilo(this,mosca5)



    override fun onDraw(c: Canvas) {
        try
        {
            super.onDraw(c)
            c.drawARGB(255, 213, 245, 227)//PINTAR FONDO
            BStart.pintarMosaca(c)
            p.textSize=60f
            c.drawText(puntaje,300f,50f,p)
            //DIBUJAR MOSCAS VIVIAS
            mosca1.pintarMosaca(c)
            mosca2.pintarMosaca(c)
            mosca3.pintarMosaca(c)
            mosca4.pintarMosaca(c)
            mosca5.pintarMosaca(c)
            //TIEMPO DE ESPERA
            desaparecer()
            sangre1.pintarMosaca(c)
            sangre2.pintarMosaca(c)
            sangre3.pintarMosaca(c)
            sangre4.pintarMosaca(c)
            sangre5.pintarMosaca(c)
            joins(1)
            //TIMER
            timer.onTick(1)
            TRestante=60-CTimer/50
            c.drawText("Tiempo = "+TRestante,700f,50f,p)
            if(TRestante<=0)
            {
                FinHilos()
                c.drawText("Tiempo = "+TRestante,700f,50f,p)
                if(puntos>=moscas)
                {
                    c.drawText("¡CONGRATULATIONS - GANADOR!",200f,700f,p)
                }
                else
                {
                    c.drawText("¡GAME OVER - PERDEDOR!",200f,700f,p)
                }
            }

            //INICIO
            if(inicio==true)
            {
                try
                {
                    HMosca1.start()
                    HMosca2.start()
                    HMosca3.start()
                    HMosca4.start()
                    HMosca5.start()
                }
                catch (e: Exception)
                {
                    try
                    {
                        joins(2)
                    }
                    catch (e:InterruptedException){}}
                inicio=false //Termina el juego
            }
        }
        catch(e:InterruptedException)
        {

            joins(1)
        }
    }
    //TIMER
    var timer =object: CountDownTimer(TTOTAL.toLong(),LAPSO.toLong()){
        override fun onTick(p0: Long)
        {
            CTimer++
        }
        override fun onFinish()
        {
            start()
        }
    }
    //METODO PARA FINALIZAR LOS HILOS
    fun FinHilos()
    {
        HMosca1.EnJuego=false
        HMosca2.EnJuego=false
        HMosca3.EnJuego=false
        HMosca4.EnJuego=false
        HMosca5.EnJuego=false
        BStart.repintar(0,0)
        TRestante=0
        inicio=false
    }
    //METODO PARA INVOCAR LOS JOINS
    fun joins(tiempo:Int)
    {
        HMosca1.join(tiempo.toLong())
        HMosca2.join(tiempo.toLong())
        HMosca3.join(tiempo.toLong())
        HMosca4.join(tiempo.toLong())
        HMosca5.join(tiempo.toLong())
    }

    //METODO (QUITA MANCHAS)
    fun desaparecer()
    {
        if (HMosca5.espera>=99)
        {
            sangre1.repintar(-1000,-1000)
        }
        if (HMosca1.espera>=99)
        {
            sangre2.repintar(-1000,-1000)
        }
        if (HMosca2.espera>=99)
        {
            sangre3.repintar(-1000,-1000)
        }

        if (HMosca3.espera>=99)
        {
            sangre4.repintar(-1000,-1000)
        }
        if (HMosca4.espera>=99)
        {
            sangre5.repintar(-1000,-1000)
        }
    }



    //jE
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action)
        {
            MotionEvent.ACTION_DOWN ->
            {
                //BOTON
                if(BStart.AreaDeToque(event.x,event.y)==true)
                {
                    BStart.repintar(-100,-100)
                    inicio=true
                    TRestante=60-CTimer/50
                }

                if(mosca1.AreaDeToque(event.x,event.y)==true)
                {
                    if(TRestante>=0)
                    {
                        puntaje = "Muertes = "+puntos++
                        sangre1.repintar(event.x.toInt(),event.y.toInt())
                        HMosca1.MoscaViva=false
                        mosca1.repintar(1900,1900)
                    }
                }
                if(mosca2.AreaDeToque(event.x,event.y)==true)
                {
                    if(TRestante>=0)
                    {
                        puntaje = "Muertes = "+puntos++
                        sangre1.repintar(event.x.toInt(),event.y.toInt())
                        HMosca2.MoscaViva=false
                        mosca2.repintar(1900,1900)
                    }
                }
                if(mosca3.AreaDeToque(event.x,event.y)==true)
                {
                    if(TRestante>=0)
                    {
                        puntaje = "Muertes = "+puntos++
                        sangre1.repintar(event.x.toInt(),event.y.toInt())
                        HMosca3.MoscaViva=false
                        mosca3.repintar(1900,1900)
                    }
                }
                if(mosca4.AreaDeToque(event.x,event.y)==true)
                {
                    if(TRestante>=0)
                    {
                        puntaje = "Muertes = "+puntos++
                        sangre1.repintar(event.x.toInt(),event.y.toInt())
                        HMosca4.MoscaViva=false
                        mosca4.repintar(1900,1900)
                    }
                }
                if(mosca5.AreaDeToque(event.x,event.y)==true)
                {
                    if(TRestante>=0)
                    {
                        puntaje = "Muertes = "+puntos++
                        sangre1.repintar(event.x.toInt(),event.y.toInt())
                        HMosca5.MoscaViva=false
                        mosca5.repintar(1900,1900)
                    }
                }

            }
            MotionEvent.ACTION_MOVE ->{

            }
            MotionEvent.ACTION_UP->{

            }
        }
        invalidate()
        return true
    }

}


