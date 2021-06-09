package its.nugrohodimas.core.data.source.remote.response

import androidx.annotation.NonNull
import androidx.room.PrimaryKey

data class VaccineResponse(
    var idVaccine: Int,
    var vaccineName: String,
    var vaccineFunction: String,
    var date: String = "",
    var time: String = ""
)
