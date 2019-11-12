package ali.khaleghi.contactlist.viewmodel

import ali.khaleghi.contactlist.data.db.entity.Contact
import ali.khaleghi.contactlist.data.repository.ContactsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ContactsViewModel(private var repository: ContactsRepository): ViewModel() {

    private var contactsList: LiveData<List<Contact>> = repository.getAllContacts()

    fun insert(contact: Contact) {
        repository.insert(contact)
    }

    fun update(contact: Contact) {
        repository.update(contact)
    }

    fun deleteContact(contact: Contact) {
        repository.deleteContact(contact)
    }

    fun getAllContacts(): LiveData<List<Contact>> {
        return contactsList
    }
}