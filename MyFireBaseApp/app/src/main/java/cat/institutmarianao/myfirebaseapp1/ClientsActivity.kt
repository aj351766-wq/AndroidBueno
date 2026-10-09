package cat.institutmarianao.myfirebaseapp1

import android.annotation.SuppressLint
import android.app.AlertDialog
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.handleCoroutineException

class ClientsActivity : AppCompatActivity() {
    private val db = Firebase.firestore

    private lateinit var recyclerView: RecyclerView
    private lateinit var clientAdapter: ClientAdapter
    private val clientList = mutableListOf<Client>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_clients)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnRegDB= findViewById<Button>(R.id.btnRegDB)
        val btnLogOut = findViewById<Button>(R.id.btnLogOut)

        // Set up RecyclerView
        recyclerView = findViewById(R.id.clientsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // The ClientAdapter get each element from the clientList and place it in the element layout (item_client)
        setupRecyclerView(recyclerView)


        recyclerView.adapter = clientAdapter

        // Load data from Firestore
        loadClientsFromFirestore()


        btnRegDB.setOnClickListener {
            showRegisterDialog()
        }

        btnLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            finish()
        }

    }
    private fun setupRecyclerView(recyclerView: RecyclerView) {
        clientAdapter = ClientAdapter(
            mutableListOf(),
            onDeleteClick = { client -> showDeleteDialog(client) }

        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = clientAdapter
    }

    private fun showDeleteDialog(client : Client){

    }
    private fun showRegisterDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_register, null)
        val ageEdittext = dialogView.findViewById<EditText>(R.id.ageEditText)
        val nameEditText = dialogView.findViewById<EditText>(R.id.nameEditText)

        AlertDialog.Builder(this).setTitle("Registrat en l'aplicació:").setView(dialogView)
            .setPositiveButton("Register") { _, _ ->

                val age = ageEdittext.text.toString().toIntOrNull()
                val name = nameEditText.text.toString().trim()

                val email = intent.getStringExtra("email").toString()
                val password = intent.getStringExtra("password").toString()

                if(age != null && name != null){
                    registrarUsuarioDB(email, name, password, age)
                }
                else{
                    Toast.makeText(
                        this, "Register incorrect email or error not well formated or age null/invalid", Toast.LENGTH_SHORT
                    ).show()
                }

            }.setNegativeButton("Cancel", null).show()
    }



    private fun registrarUsuarioDB(email: String, name : String, password: String, age : Int){

        val db = Firebase.firestore
        val client = Client(
            name = name,
            email = email,
            age = age
        )
        db.collection("clients")
            .document(email) // Fem servir l'email com a ID del document
            .set(client)
            .addOnSuccessListener { documentReference ->
                Log.d("Firestore", "Document saved with ID: $email")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }


    }



    private fun loadClientsFromFirestore() {
        // * Firestore get all documents from collection * //
        Log.d("PRUEBA", "ESTOY EN MAIN ACTIVITY")
        db.collection("clients").get().addOnSuccessListener {
                result ->
            val newListClients=result.toObjects(Client :: class.java) // clear list before get new data

            clientAdapter.updateData(newListClients)
            Log.d("Firestore", "Datos cargados correctamente: ${newListClients.size} clientes." + " Client list size: ${clientList.size}")
        }.addOnFailureListener { exception ->
            Log.w("Firestore", "Error getting documents.", exception)
        }
    }


}

