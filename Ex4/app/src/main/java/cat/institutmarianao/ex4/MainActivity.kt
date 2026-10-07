package cat.institutmarianao.ex4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale
import java.util.Locale.getDefault

class MainActivity : AppCompatActivity() {
    private val enterNameLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        var answer = ""
        val chooseText= findViewById<TextView>(R.id.AnswerText)
        chooseText.visibility= View.VISIBLE

        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val type = data?.extras?.getString("TYPE")

            if(type=="name"){
                val name=data?.extras?.getString("Name").toString()
                answer = "The name entered is $name"
            }

            else if (type=="age"){
                val age=data?.extras?.getInt("Age")
                answer = "The age entered is $age"
            }

        }
        chooseText.text = answer

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAge= findViewById<Button>(R.id.BtnAge)
        val btnName= findViewById<Button>(R.id.BtnName)


        btnAge.setOnClickListener {
            val intent= Intent(this, UserActivity :: class.java).apply{
                putExtra("TEXT", "age")
            }

            enterNameLauncher.launch(intent)
        }

        btnName.setOnClickListener {
            val intent= Intent(this, UserActivity :: class.java).apply{
                putExtra("TEXT", "name")
            }

            enterNameLauncher.launch(intent)
        }








    }


}