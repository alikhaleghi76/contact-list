package ali.khaleghi.contactlist.view.adapter


import ali.khaleghi.contactlist.R
import ali.khaleghi.contactlist.data.db.entity.Contact
import ali.khaleghi.contactlist.view.dialog.AddContactDialog
import ali.khaleghi.contactlist.view.dialog.DeleteContactDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ContactHolder>() {

    private var contactsList: List<Contact> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_item, parent, false)
        return ContactHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val contact = contactsList[position]
        holder.name.text = contact.name
        holder.address.text = contact.address

        holder.background.setOnClickListener {
            val supportFragmentManager = (holder.delete.context as AppCompatActivity)
                    .supportFragmentManager
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val prev = supportFragmentManager.findFragmentByTag("dialog")
            if (prev != null) {
                fragmentTransaction.remove(prev)
            }
            fragmentTransaction.addToBackStack(null)
            val dialogFragment = AddContactDialog.newInstance(contact)
            dialogFragment.show(fragmentTransaction, "dialog")
        }
        holder.delete.setOnClickListener {
            val supportFragmentManager = (holder.delete.context as AppCompatActivity)
                    .supportFragmentManager
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val prev = supportFragmentManager.findFragmentByTag("delete_dialog")
            if (prev != null) {
                fragmentTransaction.remove(prev)
            }
            fragmentTransaction.addToBackStack(null)
            val dialogFragment = DeleteContactDialog.newInstance(contact)
            dialogFragment.show(fragmentTransaction, "delete_dialog")
        }
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    fun setContacts(contactsList: List<Contact>) {
        this.contactsList = contactsList
        notifyDataSetChanged()
    }

    inner class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val background: CardView = itemView.findViewById(R.id.background)
        val name: TextView = itemView.findViewById(R.id.name)
        val address: TextView = itemView.findViewById(R.id.address)
        val delete: ImageView = itemView.findViewById(R.id.delete)
    }
}