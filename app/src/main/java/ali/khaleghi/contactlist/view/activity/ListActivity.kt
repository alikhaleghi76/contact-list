package ali.khaleghi.contactlist.view.activity

import ali.khaleghi.contactlist.data.db.entity.Contact
import ali.khaleghi.contactlist.view.adapter.ContactsAdapter
import ali.khaleghi.contactlist.view.dialog.AddContactDialog
import ali.khaleghi.contactlist.viewmodel.ContactsViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import org.koin.android.ext.android.inject
import androidx.recyclerview.widget.RecyclerView
import android.R
import android.view.View


class ListActivity : AppCompatActivity() {

    private val contactsViewModel: ContactsViewModel by inject()
    private val adapter: ContactsAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ali.khaleghi.contactlist.R.layout.activity_list)

        initViews()

        contactsViewModel.getAllContacts().observe(this,
                Observer<List<Contact>> { list ->
                    noContact.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE

                    list?.let {
                        adapter.setContacts(it)
                    }
                })

    }

    private fun initViews() {
        setupAddButton()
        setupRecyclerView()
    }

    private fun setupAddButton() {
        buttonAddContact.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val prev = supportFragmentManager.findFragmentByTag("dialog")
            if (prev != null) {
                fragmentTransaction.remove(prev)
            }
            fragmentTransaction.addToBackStack(null)
            val dialogFragment = AddContactDialog.newInstance(null)
            dialogFragment.show(fragmentTransaction, "dialog")
        }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && buttonAddContact.isShown)
                    buttonAddContact.hide()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    buttonAddContact.show()
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }
}
