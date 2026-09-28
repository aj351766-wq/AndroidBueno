package cat.institutmarianao.myfirebaseapp1

import android.os.Bundle
import android.util.Log
import android.widget.Button
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_clients)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnLogOut = findViewById<Button>(R.id.btnLogOut)

        btnLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            finish()
        }

        // Set up RecyclerView
        recyclerView = findViewById(R.id.clientsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // The ClientAdapter get each element from the clientList and place it in the element layout (item_client)
        clientAdapter = ClientAdapter(clientList)


        recyclerView.adapter = clientAdapter

        // Load data from Firestore
        loadClientsFromFirestore()
    }

    private fun loadClientsFromFirestore() {
        // * Firestore get all documents from collection * //
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

