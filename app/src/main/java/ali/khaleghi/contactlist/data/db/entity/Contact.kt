package ali.khaleghi.contactlist.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contact(
    var name: String,
    var address: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}