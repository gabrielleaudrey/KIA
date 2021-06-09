package its.nugrohodimas.core.data

import its.nugrohodimas.core.data.source.local.LocalDataSource
import its.nugrohodimas.core.data.source.local.entity.VaccineEntity
import its.nugrohodimas.core.data.source.remote.RemoteDataSource
import its.nugrohodimas.core.data.source.remote.network.ApiResponse
import its.nugrohodimas.core.data.source.remote.response.VaccineResponse
import its.nugrohodimas.core.domain.model.Vaccine
import its.nugrohodimas.core.domain.repository.IKiaRepository
import its.nugrohodimas.core.utils.AppExecutors
import its.nugrohodimas.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class KiaRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IKiaRepository {
    override fun getAllVaccine(): Flow<Resource<List<Vaccine>>> =
        object : NetworkBoundResource<List<Vaccine>, List<VaccineResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Vaccine>> {
                return localDataSource.getAllVaccine()
                    .map { DataMapper.mapEntitiesToDomainVaccine(it) }
            }

            override fun shouldFetch(data: List<Vaccine>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<VaccineResponse>>> =
                remoteDataSource.getAllVaccine()

            override suspend fun saveCallResult(data: List<VaccineResponse>) {
                val vaccineList = DataMapper.mapResponsesToEntitiesVaccine(data)
                localDataSource.insertVaccine(vaccineList)
            }
        }.asFlow()

    override fun getDateVaccine(): Flow<List<Vaccine>> {
        return localDataSource.getDateVaccine().map {
            DataMapper.mapEntitiesToDomainVaccine(it)
        }
    }

    override suspend fun insertDataVaccine(vaccine: VaccineEntity) {
        localDataSource.insertDataVaccine(vaccine)
    }
}
