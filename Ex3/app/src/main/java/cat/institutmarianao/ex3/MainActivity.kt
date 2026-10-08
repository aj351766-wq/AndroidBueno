package cat.institutmarianao.ex3

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {

    private lateinit var btnDoom: Button
    private lateinit var name: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        configurarWindowInset()

        inicializarVariablesLayout()

        configurarFocusName()

        configurarAparicionBoton()

        configurarBtnDoom()


    }


    private fun configurarBtnDoom(){
        btnDoom.setOnClickListener {
            val textName= name.text.toString().trim()

            if (!(textName.isEmpty())){
                val intent= Intent(this, WelcomeActivity::class.java).apply {
                    putExtra("name", textName)
                }
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "NAME NULL!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun configurarAparicionBoton(){
        name.doOnTextChanged { text, _, _, _ ->
            val textName = text.toString().trim().lowercase()
            text?.isEmpty()?.let {
                if(!(it)) {
                    btnDoom.visibility = View.VISIBLE
                    Toast.makeText(this, "THE NAME SHALL PASS!", Toast.LENGTH_SHORT).show()
                    true
                } else{
                    btnDoom.visibility=View.GONE
                    Toast.makeText(this, "THE NAME SHALL NOT PASS!", Toast.LENGTH_SHORT).show()
                    true
                }
            }

        }

    }
    private fun configurarFocusName(){
        name.setOnFocusChangeListener{_, hasFocus ->
            if(hasFocus){
                name.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#00CED1"))
            }
            else{
                name.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#9E9E9E"))
            }

        }
    }
    private fun inicializarVariablesLayout(){
        btnDoom = findViewById<Button>(R.id.btnDoom)
        name= findViewById<EditText>(R.id.Name)
    }
    private fun configurarWindowInset(){

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}