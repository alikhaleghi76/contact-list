package ali.khaleghi.contactlist.data.db.dao

import ali.khaleghi.contactlist.data.db.entity.Contact
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update



@Dao
interface ContactsDao {

    @Insert
    fun insert(contact: Contact)

    @Update
    fun update(contact: Contact)

    @Delete
    fun delete(contact: Contact)

    @Query("SELECT * FROM contacts_table ")
    fun getAllContacts(): LiveData<List<Contact>>

}