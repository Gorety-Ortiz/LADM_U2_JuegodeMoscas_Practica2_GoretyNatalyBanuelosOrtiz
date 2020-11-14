package mx.tecnm.tepic.ladm_u2_juegodemoscas

import kotlin.random.Random

class hilo (p: Lienzo, MO:Moscas):Thread(){
    var espera = 0
    var puntero = p
    var moscaX = MO.anchoX
    var moscaY = MO.altoY
    var mosca = MO
    var EnJuego =true
    var MoscaViva = true
    var limiteX=true
    var limiteY=true

    override fun run() {
        super.run()

        while(EnJuego){
            if(MoscaViva==true)
            {
                Rebote()
            }
            else
            {
                if(espera==100)
                {
                    MoscaViva=true
                    moscaX = Random.nextInt(0,puntero.width).toFloat()
                    moscaY = Random.nextInt(0,puntero.width).toFloat()
                    mosca.repintar(moscaX.toInt(),moscaY.toInt())
                    espera=0
                }
                else
                {
                    espera++
                    puntero.invalidate()
                }
            }
            sleep(2)
        }
    }

    fun Rebote()
    {
        if(limiteY==true)
        {
            moscaY = moscaY + 2
            if(moscaY >= puntero.width)
            {
                limiteY=false
            }
        }
        else
        {
            moscaY = moscaY - 2
            if(moscaY <= 0f)
            {
                limiteY=true
            }
        }
        if(limiteX==true)
        {
            moscaX= moscaX + 2
            if(moscaX >= puntero.width)
            {
                limiteX=false
            }
        }
        else
        {
            moscaX = moscaX - 2
            if(moscaX <= 0f)
            {
                limiteX=true
            }
        }

        mosca.repintar(moscaX.toInt(), moscaY.toInt())
        puntero.invalidate()
    }
}