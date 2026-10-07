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
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle= intent.extras
        val usernameActivityMain= bundle?.getString("username").toString().trim()
        val btnClose= findViewById<Button>(R.id.CloseBtn)

        val welcomeText= findViewById<TextView>(R.id.Welcome)

        welcomeText.text= "Welcome $usernameActivityMain"

        btnClose.setOnClickListener {
            finish()
        }



    }
}