package its.nugrohodimas.core.utils

import its.nugrohodimas.core.data.source.local.entity.VaccineEntity
import its.nugrohodimas.core.data.source.remote.response.VaccineResponse
import its.nugrohodimas.core.domain.model.Vaccine

object DataMapper {

    fun mapResponsesToEntitiesVaccine(input: List<VaccineResponse>): List<VaccineEntity> {
        val vaccineList = ArrayList<VaccineEntity>()
        input.map {
            val vaccine = VaccineEntity(
                idVaccine = it.idVaccine,
                vaccineName = it.vaccineName,
                vaccineFunction = it.vaccineFunction,
                date = it.date,
                time = it.time,
                status = false
            )
            vaccineList.add(vaccine)
        }
        return vaccineList
    }

    fun mapEntitiesToDomainVaccine(input: List<VaccineEntity>): List<Vaccine> =
        input.map {
            Vaccine(
                idVaccine = it.idVaccine,
                vaccineName = it.vaccineName,
                vaccineFunction = it.vaccineFunction,
                date = it.date,
                time = it.time,
                status = it.status
            )
        }
}