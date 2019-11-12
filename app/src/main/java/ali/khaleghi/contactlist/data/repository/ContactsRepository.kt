package ali.khaleghi.contactlist.data.repository

import ali.khaleghi.contactlist.data.db.dao.ContactsDao
import ali.khaleghi.contactlist.data.db.entity.Contact
import androidx.lifecycle.LiveData

class ContactsRepository(private val contactsDao: ContactsDao) {

    private val contactsList: LiveData<List<Contact>> = contactsDao.getAllContacts()


    fun insert(contact: Contact) {
        contactsDao.insert(contact)
    }

    fun update(contact: Contact) {
        contactsDao.update(contact)
    }

    fun deleteContact(contact: Contact) {
        contactsDao.delete(contact)
    }

    fun getAllContacts(): LiveData<List<Contact>> {
        return contactsList
    }

}