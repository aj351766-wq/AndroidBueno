package cat.institutmarianao.exercici_1_author

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //Elements layout

    private lateinit var btnAuthor : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        configurarWindowView()

        inicializarContenidoLayout()

        configurarBtnAuthor()


    }

    private  fun configurarBtnAuthor(){
        btnAuthor.setOnClickListener {
            startActivity(Intent(this, Author_Activity::class.java))
        }
    }

    private fun inicializarContenidoLayout(){
        btnAuthor = findViewById<Button>(R.id.btnAuthor)
    }
    private fun configurarWindowView(){
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}