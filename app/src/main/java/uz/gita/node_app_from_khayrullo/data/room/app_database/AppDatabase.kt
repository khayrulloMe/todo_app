package uz.gita.node_app_from_khayrullo.data.room.app_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.node_app_from_khayrullo.data.room.dao.NoteDao
import uz.gita.node_app_from_khayrullo.data.room.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): NoteDao

    companion object {
        private var instance: AppDatabase? = null
        fun init(context: Context) {
            instance =
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "notedb")
                    .build()
        }

        fun getInstance() = instance!!
    }
}