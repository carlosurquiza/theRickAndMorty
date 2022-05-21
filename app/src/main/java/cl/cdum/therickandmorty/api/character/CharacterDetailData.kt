package cl.cdum.therickandmorty.api.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characterDetailDataTable")
data class CharacterDetailData(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("episode")
    val episode: List<String>? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("location")
    val location: Location? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin")
    val origin: Origin? = null,
    @SerializedName("species")
    val species: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("url")
    val url: String? = null
)