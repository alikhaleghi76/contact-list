package ali.khaleghi.contactlist.data.db

import ali.khaleghi.contactlist.data.db.dao.ContactsDao
import ali.khaleghi.contactlist.data.db.entity.Contact
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Contact::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao


    companion object {
        private var instance: ContactsDatabase? = null

        fun getInstance(context: Context): ContactsDatabase {
            if (instance == null) {
                synchronized(ContactsDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactsDatabase::class.java,
                        "contacts_db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }

    }
}