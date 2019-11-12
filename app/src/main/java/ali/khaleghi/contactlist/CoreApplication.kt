package ali.khaleghi.contactlist

import ali.khaleghi.contactlist.di.dbModule
import ali.khaleghi.contactlist.di.repositoryModule
import ali.khaleghi.contactlist.di.uiModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoreApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoreApplication)
            modules(listOf(dbModule, repositoryModule,  uiModule))
        }
    }
}