package cat.institutmarianao.exercici_1_author

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Author_Activity : AppCompatActivity() {


    //Element layout
    private lateinit var btnClose: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_clients_view)

        configurarWindowView()

        inicializarContenidoLayout()

        configurarBtnClose()

    }

    private fun configurarBtnClose() {
        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun inicializarContenidoLayout() {
        btnClose = findViewById<Button>(R.id.btnClose)
    }

    private fun configurarWindowView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}