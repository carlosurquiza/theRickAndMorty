package cl.cdum.therickandmorty.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.cdum.therickandmorty.api.character.CharacterDetailData
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterDetail(characterDetail: CharacterDetailData)

    @Query("SELECT * FROM characterDetailDataTable")
    fun getAllCharacterDetail(): Flow<CharacterDetailData>

    @Query("DELETE FROM characterDetailDataTable")
    suspend fun deleteAllCharacterDetail()
}