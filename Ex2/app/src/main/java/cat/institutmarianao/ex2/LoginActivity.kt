package cat.institutmarianao.ex2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {


    // Elements layout
    private lateinit var btnClose: Button
    private lateinit var welcomeText: TextView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_login)

        configurarWindowView()

        inicializarVariablesLayout()

        configurarBtnClose()

    }

    private fun  configurarBtnClose(){
        btnClose.setOnClickListener {
            finish()
        }
    }
    private fun obtenerUsername() : String{
        return intent.extras?.getString("username")?.trim() ?: ""
    }

    private fun mostrarBienvenida(){
        val usernameActivityMain=obtenerUsername()

        welcomeText.text= "Welcome $usernameActivityMain"
    }
    private fun inicializarVariablesLayout(){
        btnClose = findViewById(R.id.CloseBtn)
        welcomeText = findViewById(R.id.Welcome)
    }
    private fun  configurarWindowView(){
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}