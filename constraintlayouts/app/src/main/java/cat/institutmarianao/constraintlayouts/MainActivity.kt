package cat.institutmarianao.constraintlayouts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
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

        val btnSignIn= findViewById<Button>(R.id.btnSignIn)

        btnSignIn.setOnClickListener(){
            val intent = Intent(Intent.ACTION_SEND).apply {
                val recipientArray = arrayOf("soporte@tuempresa.com")
                putExtra(Intent.EXTRA_EMAIL, recipientArray)
            }
            startActivity(intent)
        }



    }
}