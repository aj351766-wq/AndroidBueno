package cat.institutmarianao.myfirebaseapp1

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerButton= findViewById<Button>(R.id.registerButton)
        val accessDialogButton = findViewById<Button>(R.id.accessDialogButton)


        loginButton.setOnClickListener {
            loginForm(emailEditText, passwordEditText)
        }
        registerButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            registrarUsuario(email, password)
        }
        accessDialogButton.setOnClickListener {
            if (FirebaseAuth.getInstance().currentUser != null) {
                // User already signed in, launch ClientsActivity
                startActivity(Intent(this, ClientsActivity::class.java))
            } else {
                // Not signed in, show custom login dialog
                showLoginDialog()
            }
        }
    }


    private fun showLoginDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_login, null)
        val emailEditText = dialogView.findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = dialogView.findViewById<EditText>(R.id.passwordEditText)

        AlertDialog.Builder(this).setTitle("Inicia sessió").setView(dialogView)
            .setPositiveButton("Login") { _, _ ->
                val email = emailEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                login(email, password)
            }.setNegativeButton("Cancel", null).show()
    }

  /*  private fun showRegisterDialog() { //Falta hacer REGISTER a la base de datos y cambiar vista de adapter en la pantalla principal
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_register, null)
        val emailEditText = dialogView.findViewById<EditText>(R.id.emailEditText)
        val ageEdittext = dialogView.findViewById<EditText>(R.id.ageEditText)
        val passwordEditText = dialogView.findViewById<EditText>(R.id.passwordEditText)

        AlertDialog.Builder(this).setTitle("Registrat en l'aplicació:").setView(dialogView)
            .setPositiveButton("Register") { _, _ ->
                val email = emailEditText.text.toString().trim()
                val age = ageEdittext.text.toString().toIntOrNull()
                val password = passwordEditText.text.toString().trim()

                if(age != null){
                    registrarUsuario(email, password, age)
                }
                else{
                    Toast.makeText(
                        this, "Register incorrect email or error not well formated or age null/invalid", Toast.LENGTH_SHORT
                    ).show()
                }

            }.setNegativeButton("Cancel", null).show()
    }*/



    private fun registrarUsuario(email: String, password: String, age : Int){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "User successfully created!", Toast.LENGTH_SHORT).show()


                    val clientsActivity = Intent(this, ClientsActivity::class.java)
                    startActivity(clientsActivity)

                } else {
                    Toast.makeText(
                        this, "Register incorrect email or error not well formated", Toast.LENGTH_SHORT
                    ).show()
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun registrarUsuario(email: String, password: String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "User successfully created!", Toast.LENGTH_SHORT).show()


                    val clientsActivity = Intent(this, ClientsActivity::class.java)
                    startActivity(clientsActivity)

                } else {
                    Toast.makeText(
                        this, "Register incorrect email or error not well formated", Toast.LENGTH_SHORT
                    ).show()
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun loginForm(emailEditText: EditText, passwordEditText: EditText) {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        login(email, password)
    }

    private fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(
                this, "Email and password required", Toast.LENGTH_SHORT
            ).show()
            return
        }

        // * Firebase login * //
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Welcome ${FirebaseAuth.getInstance().currentUser?.email}",
                        Toast.LENGTH_SHORT
                    ).show()
                    val clientsActivity = Intent(this, ClientsActivity::class.java)
                    startActivity(clientsActivity)
                } else {
                    Toast.makeText(
                        this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT
                    ).show()



                }
            }




    }

}