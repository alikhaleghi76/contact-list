package ali.khaleghi.contactlist.view.dialog

import ali.khaleghi.contactlist.R
import ali.khaleghi.contactlist.data.db.entity.Contact
import ali.khaleghi.contactlist.viewmodel.ContactsViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import org.koin.android.ext.android.inject

class AddContactDialog : DialogFragment() {

    private val contactsViewModel: ContactsViewModel by inject()

    private var contact: Contact? = null

    companion object {
        fun newInstance(contact: Contact?) : AddContactDialog {
            val contactDialog = AddContactDialog()
            contactDialog.contact = contact
            return contactDialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.dialog_add_contact, container, false)

        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        initViews(layout)

        return layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    private fun initViews(layout: View) {

        val name = layout.findViewById<EditText>(R.id.name)
        val address = layout.findViewById<EditText>(R.id.address)
        val save = layout.findViewById<TextView>(R.id.save)
        val cancel = layout.findViewById<TextView>(R.id.cancel)

        if (contact != null) {
            name.setText(contact!!.name)
            address.setText(contact!!.address)
            save.text = getString(R.string.edit)
            name.setSelection(contact!!.name.length)
        }

        cancel.setOnClickListener {
            dismiss()
        }

        save.setOnClickListener {
            if (name.text.isEmpty()) {
                Toast.makeText(context, getString(R.string.empty_name), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (contact != null) { //edit
                contact!!.name = name.text.toString()
                contact!!.address = address.text.toString()
                contactsViewModel.update(contact!!)
                Toast.makeText(context, getString(R.string.contact_edited), Toast.LENGTH_SHORT).show()
                dismiss()
                return@setOnClickListener
            }

            val newContact = Contact(
                    name.text.toString(),
                    address.text.toString()
            )
            contactsViewModel.insert(newContact)

            Toast.makeText(context, getString(R.string.contact_added), Toast.LENGTH_SHORT).show()

            dismiss()
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}