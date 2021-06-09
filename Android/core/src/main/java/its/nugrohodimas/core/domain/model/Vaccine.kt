package its.nugrohodimas.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vaccine(
    var idVaccine: Int = 0,
    var vaccineName: String = "",
    var vaccineFunction: String = "",
    var date: String = "",
    var time: String = "",
    var hospital: String = "",
    var status: Boolean = false
) : Parcelable
