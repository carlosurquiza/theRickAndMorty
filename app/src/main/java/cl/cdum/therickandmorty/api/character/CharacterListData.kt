package cl.cdum.therickandmorty.api.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characterListDataTable")
data class CharacterListData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("info")
    val info: Info? = null,
    @SerializedName("results")
    val result: List<Result>? = null
)