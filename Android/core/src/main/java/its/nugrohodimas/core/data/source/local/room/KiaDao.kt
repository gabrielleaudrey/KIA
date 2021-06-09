package its.nugrohodimas.core.data.source.local.room

import androidx.room.*
import its.nugrohodimas.core.data.source.local.entity.VaccineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KiaDao {

    @Query("SELECT * FROM vaccine")
    fun getAllVaccineFlow(): Flow<List<VaccineEntity>>

    @Query("SELECT * FROM vaccine")
    fun getAllVaccine(): List<VaccineEntity>

    @Query("SELECT * FROM vaccine ORDER BY date DESC")
    fun getUpcomingVaccine(): List<VaccineEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVaccine(vaccine: List<VaccineEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListVaccine(vaccine: List<VaccineEntity>)

    @Update
    fun updateDataVaccine(vaccine: VaccineEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataVaccine(vaccine: VaccineEntity)
}
