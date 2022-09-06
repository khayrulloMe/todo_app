package uz.gita.node_app_from_khayrullo.app

import android.app.Application
import uz.gita.node_app_from_khayrullo.data.room.app_database.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
    }
}