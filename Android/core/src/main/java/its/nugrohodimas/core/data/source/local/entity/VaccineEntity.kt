package its.nugrohodimas.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "vaccine")
data class VaccineEntity(
    @PrimaryKey
    @NonNull
    var idVaccine: Int = 0,
    var vaccineName: String = "",
    var vaccineFunction: String = "",
    var vaccineIndication: String = "",
    var vaccinePostEvent: String = "",
    var date: String = "",
    var time: String = "",
    var hospital: String = "",
    var status: Boolean = false
) : Parcelable