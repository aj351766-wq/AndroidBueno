package cat.institutmarianao.ex3

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {


    //Elementos Layout

    private lateinit var textPoints: TextView
    private lateinit var textWelcome: TextView
    private lateinit var btnBackAgain: Button

    //Puntos y usuarios

    private val usersPoints = mapOf(
        "gandalf" to 312, "frodo" to 222, "saruman" to 489
    )


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        configurarView()

        inicializarVariablesLayout()

        mostrarBienvenida()

        mostrarPuntuacion()

        configurarBtnBack()


    }

    private fun obtenerNombre(): String? {
        return intent.extras?.getString("name")
    }

    private fun inicializarVariablesLayout() {
        textPoints = findViewById<TextView>(R.id.txtScore)
        textWelcome = findViewById<TextView>(R.id.txtWelcome)
        btnBackAgain = findViewById<Button>(R.id.btnBackAgain)
    }

    private fun configurarView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun mostrarPuntuacion() {
        val name = obtenerNombre()

        if (name?.lowercase() in usersPoints) {
            val points = usersPoints[name?.lowercase()]
            textPoints.text = "Your Score is $points"

        } else {
            textPoints.text = "Your Score is 0"
        }
    }

    private fun mostrarBienvenida() {
        val name = obtenerNombre()

        textWelcome.text = "Welcome, my Lord $name!"
    }

    private fun configurarBtnBack() {
        obtenerNombre()

        btnBackAgain.setOnClickListener {
            finish()
        }
    }
}