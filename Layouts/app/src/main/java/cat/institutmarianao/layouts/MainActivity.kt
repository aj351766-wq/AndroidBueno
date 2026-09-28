package cat.institutmarianao.layouts

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    companion object{
        val PARAM_POINTS=19
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.BtnSend).setOnClickListener {
            sendAction()
        }


    }

    private fun sendAction(){
        startActivity(
            Intent(Intent.ACTION_SEND).apply {
                // The intent does not have a URI, so declare the "text/plain" MIME type
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("jan@example.com")) // recipients
                putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                putExtra(Intent.EXTRA_TEXT, "Email message text")
                putExtra(
                    Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment")
                )
                // You can also attach multiple items by passing an ArrayList of Uris
            })
    }

    private fun secondAction(){
        val etUserText=findViewById<EditText>(R.id.UserText)

        startActivity(
            Intent(this, MainActivity2::class.java).apply {
                putExtra(
                    "user", etUserText
                )
                putExtra(PARAM_POINTS, 18)
            }
        )

    }


}