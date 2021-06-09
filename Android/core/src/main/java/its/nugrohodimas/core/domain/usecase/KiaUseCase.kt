package its.nugrohodimas.core.domain.usecase

import its.nugrohodimas.core.data.Resource
import its.nugrohodimas.core.data.source.local.entity.VaccineEntity
import its.nugrohodimas.core.domain.model.Vaccine
import kotlinx.coroutines.flow.Flow

interface KiaUseCase {
    fun getDateVaccine(): Flow<List<Vaccine>>
    suspend fun insertDataVaccine(vaccine: VaccineEntity)
}