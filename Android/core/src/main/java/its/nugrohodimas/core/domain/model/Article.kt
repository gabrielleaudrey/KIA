package its.nugrohodimas.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    var author: String = "",
    var content: String = "",
    var date: String = "",
    var id_article: String = "",
    var title: String = "",
    var image: String = ""
) : Parcelable
