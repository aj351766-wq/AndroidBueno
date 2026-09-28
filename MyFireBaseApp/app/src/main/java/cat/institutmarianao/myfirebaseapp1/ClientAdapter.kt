package cat.institutmarianao.myfirebaseapp1


import android.util.Log
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView



class ClientAdapter(private val clients: MutableList<Client>) :
    RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    // The ViewHolder, that uses the view layout and
    class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.nameTextView)
        val emailText: TextView = itemView.findViewById(R.id.emailTextView)
        val ageText: TextView = itemView.findViewById(R.id.ageTextView)
    }

    // Create new views (invoked by the layout manager)
    // Returns a ViewHolder, that will be used for each element in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        // The view, which uses item_client layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_client, parent, false)
        return ClientViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = clients[position]

        Log.d("ADAPTER", "Mostrando: ${client.name} - ${client.email} - ${client.age}")

        holder.ageText.text = client.age.toString()
        holder.emailText.text = client.email
        holder.nameText.text = client.name
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = clients.size

    /**
     * Update the data in the adapter and notify the [RecyclerView] to refresh
     */
    fun updateData(newClients: List<Client>) {
        clients.clear()
        clients.addAll(newClients)
        notifyDataSetChanged()
    }


    }