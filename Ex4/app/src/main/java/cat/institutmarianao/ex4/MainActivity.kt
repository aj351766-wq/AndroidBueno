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


    //Elements layout
    private lateinit var btnAge: Button
    private lateinit var btnName: Button
    private lateinit var chooseText  : TextView

    //Constants text codi
    companion object{
        const val EXTRA_TEXT= "TEXT"
        const val EXTRA_TYPE = "TYPE"
        const val EXTRA_NAME = "Name"
        const val EXTRA_AGE = "Age"

        const val TYPE_NAME  = "name"
        const val TYPE_AGE = "age"
    }


    private val enterNameLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        procesarResultado(result)

    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        inicializarVariablesLayout()

        configurarWindowInset()

        configurarListeners()


    }

    private fun configurarListeners(){
        btnAge.setOnClickListener {
            abrirViewUserActivity(TYPE_AGE)
        }

        btnName.setOnClickListener {
            abrirViewUserActivity(TYPE_NAME)
        }

    }

    private fun abrirViewUserActivity(type : String){
        val intent= Intent(this, UserActivity :: class.java).apply{
            putExtra(EXTRA_TEXT, type)
        }

        enterNameLauncher.launch(intent)
    }


    private fun configurarWindowInset(){

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun inicializarVariablesLayout(){
        chooseText= findViewById<TextView>(R.id.AnswerText)
        btnAge= findViewById<Button>(R.id.BtnAge)
        btnName= findViewById<Button>(R.id.BtnName)
    }
    private fun procesarResultado (result : androidx.activity.result.ActivityResult){
        var answer = ""

        chooseText.visibility= View.VISIBLE

        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val type = data?.extras?.getString(EXTRA_TYPE)

            if(type==TYPE_NAME){
                val name=data.extras?.getString(EXTRA_NAME).toString()
                answer = "The name entered is $name"
            }

            else if (type==TYPE_AGE){
                val age=data.extras?.getInt(EXTRA_AGE)
                answer = "The age entered is $age"
            }

        }
        chooseText.text = answer
    }


}