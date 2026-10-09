package cat.institutmarianao.ex4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user)

        configurarWindowInsets()

        checkTipoFormulario()
    }

    private fun configurarWindowInsets(){
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun checkTipoFormulario(){
        val txt= intent.extras?.getString("TEXT")?.lowercase()?.trim()

        if(txt=="name"){
            formularioName()
        }
        if (txt == "age"){
            formularioAge()
        }
    }

    private fun formularioAge(){
        setContentView(R.layout.activity_age)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        btnSubmit.setOnClickListener {
            val age = findViewById<EditText>(R.id.AgeEditText).text.toString()

            if (!(age.isEmpty()))  {
                val intent = Intent()

                val intAge=age.toInt()
                intent.putExtra("TYPE", "age")
                intent.putExtra("Age", intAge)

                setResult(RESULT_OK, intent)
                finish()
            }

            else{
                Toast.makeText(this, "The field cannot be empty.",Toast.LENGTH_SHORT).show()
            }


        }
        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun formularioName(){
        setContentView(R.layout.activity_name)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        btnSubmit.setOnClickListener {
            val name = findViewById<EditText>(R.id.AgeEditText).text.toString().trim()

            if (!(name.isEmpty())) {
                val intent = Intent()

                intent.putExtra("TYPE", "name")
                intent.putExtra("Name", name)

                setResult(RESULT_OK, intent)
                finish()
            }

            else{
                Toast.makeText(this, "The field cannot be empty.",Toast.LENGTH_SHORT).show()
            }


        }

        btnCancel.setOnClickListener {
            finish()
        }
    }


}