package its.nugrohodimas.core.domain.repository

import its.nugrohodimas.core.data.Resource
import its.nugrohodimas.core.data.source.local.entity.VaccineEntity
import its.nugrohodimas.core.domain.model.Vaccine
import kotlinx.coroutines.flow.Flow

interface IKiaRepository {

    fun getAllVaccine(): Flow<Resource<List<Vaccine>>>

    fun getDateVaccine(): Flow<List<Vaccine>>

    suspend fun insertDataVaccine(vaccine: VaccineEntity)

}
