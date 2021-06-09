package its.nugrohodimas.core.data.source.remote

import android.content.Context
import android.util.Log
import its.nugrohodimas.core.data.source.remote.network.ApiResponse
import its.nugrohodimas.core.data.source.remote.network.ApiService
import its.nugrohodimas.core.data.source.remote.response.VaccineResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class RemoteDataSource(private val apiService: ApiService, private val context: Context) {

    fun getAllVaccine(): Flow<ApiResponse<List<VaccineResponse>>> {
        return flow {
            try {
                val dataList: MutableList<VaccineResponse> = mutableListOf()
                val obj = JSONObject(loadJSONFromAsset())
                val userArray = obj.getJSONArray("vaccine")
                for (i in 0 until userArray.length()) {
                    val vaccine = userArray.getJSONObject(i)
                    val list = VaccineResponse(
                        idVaccine = vaccine.getInt("id"),
                        vaccineFunction = vaccine.getString("vaccineFunc"),
                        vaccineName = vaccine.getString("name")
                    )
                    dataList.add(list)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = context.assets?.open("vaccine.json")
            val size = inputStream?.available()
            val buffer = size?.let { ByteArray(it) }
            val charset: Charset = Charsets.UTF_8
            inputStream?.read(buffer)
            inputStream?.close()
            json = buffer?.let { String(it, charset) }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json.toString()
    }
}

