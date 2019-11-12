package ali.khaleghi.contactlist.view.dialog

import ali.khaleghi.contactlist.R
import ali.khaleghi.contactlist.data.db.entity.Contact
import ali.khaleghi.contactlist.viewmodel.ContactsViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import org.koin.android.ext.android.inject

class DeleteContactDialog : DialogFragment() {

    private val contactsViewModel: ContactsViewModel by inject()

    private lateinit var contact: Contact

    companion object {
        fun newInstance(contact: Contact) : DeleteContactDialog {
            val contactDialog = DeleteContactDialog()
            contactDialog.contact = contact
            return contactDialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.dialog_delete_contact, container, false)

        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        initViews(layout)

        return layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    private fun initViews(layout: View) {

        val yes = layout.findViewById<TextView>(R.id.yes)
        val no = layout.findViewById<TextView>(R.id.no)

        no.setOnClickListener {
            dismiss()
        }

        yes.setOnClickListener {

            contactsViewModel.deleteContact(contact)

            Toast.makeText(context, getString(R.string.contact_deleted), Toast.LENGTH_SHORT).show()

            dismiss()
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}