package ali.khaleghi.contactlist.di

import ali.khaleghi.contactlist.data.db.ContactsDatabase
import ali.khaleghi.contactlist.data.repository.ContactsRepository
import ali.khaleghi.contactlist.view.adapter.ContactsAdapter
import ali.khaleghi.contactlist.viewmodel.ContactsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {
    single { ContactsDatabase.getInstance(
        context = get()
    )}
    factory { get<ContactsDatabase>().contactsDao() }
}

val repositoryModule = module {
    single { ContactsRepository(get()) }
}

val uiModule = module {
    factory { ContactsAdapter() }
    viewModel { ContactsViewModel(get()) }
}

