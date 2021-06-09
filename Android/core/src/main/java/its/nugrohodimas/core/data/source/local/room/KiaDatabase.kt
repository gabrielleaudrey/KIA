package its.nugrohodimas.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

import its.nugrohodimas.core.data.source.local.entity.VaccineEntity

@Database(entities = [VaccineEntity::class], version = 5, exportSchema = true)
abstract class KiaDatabase : RoomDatabase() {

    abstract fun kiaDao(): KiaDao
}