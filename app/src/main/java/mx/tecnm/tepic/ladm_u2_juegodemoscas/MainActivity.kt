package mx.tecnm.tepic.ladm_u2_juegodemoscas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //MANDAR A LLAMAR AL LINEZO
        setContentView(Lienzo(this))
    }
}