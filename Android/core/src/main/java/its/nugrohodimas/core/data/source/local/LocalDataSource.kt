package its.nugrohodimas.core.data.source.local

import its.nugrohodimas.core.data.source.local.entity.VaccineEntity
import its.nugrohodimas.core.data.source.local.room.KiaDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val teamDao: KiaDao) {

    fun getAllVaccine(): Flow<List<VaccineEntity>> = teamDao.getAllVaccineFlow()

    fun getDateVaccine(): Flow<List<VaccineEntity>> = teamDao.getAllVaccineFlow()

    suspend fun insertDataVaccine(vaccine: VaccineEntity) = teamDao.insertDataVaccine(vaccine)

    suspend fun insertVaccine(vaccineList: List<VaccineEntity>) = teamDao.insertVaccine(vaccineList )
}