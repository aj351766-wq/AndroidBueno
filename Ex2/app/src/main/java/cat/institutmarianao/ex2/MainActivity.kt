package cat.institutmarianao.ex2


import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnCheck = findViewById<Button>(R.id.btnCheck)

        btnCheck.setOnClickListener {
            val username= findViewById<EditText>(R.id.Username).text.toString()
            val password = findViewById<EditText>(R.id.Password).text.toString().trim()

            if(password == "abc123"){
                val intent= Intent(this, LoginActivity::class.java).apply {
                    putExtra("username", username)
                }
                startActivity(intent)
            }

            else{
                val passwordIncorrect = Html.fromHtml("<font color='red'>Wrong Password.<br>It's to easy as abc123</font>", Html.FROM_HTML_MODE_LEGACY)

                Toast.makeText(this, passwordIncorrect, Toast.LENGTH_SHORT).show()
            }



        }

    }
}