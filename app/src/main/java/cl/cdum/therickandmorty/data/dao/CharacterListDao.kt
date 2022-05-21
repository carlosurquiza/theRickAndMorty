package cl.cdum.therickandmorty.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.cdum.therickandmorty.api.character.CharacterListData
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterList(characterList: CharacterListData)

    @Query("SELECT * FROM characterListDataTable")
    fun getAllCharacterList(): Flow<CharacterListData>

    @Query("DELETE FROM characterListDataTable")
    suspend fun deleteAllCharacterList()
}