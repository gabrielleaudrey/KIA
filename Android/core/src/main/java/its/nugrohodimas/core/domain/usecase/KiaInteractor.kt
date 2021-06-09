package its.nugrohodimas.core.domain.usecase

import its.nugrohodimas.core.data.source.local.entity.VaccineEntity
import its.nugrohodimas.core.domain.repository.IKiaRepository

class KiaInteractor(private val kiaRepository: IKiaRepository) : KiaUseCase {

    override fun getDateVaccine() = kiaRepository.getDateVaccine()

    override suspend fun insertDataVaccine(vaccine: VaccineEntity) =
        kiaRepository.insertDataVaccine(vaccine)
}