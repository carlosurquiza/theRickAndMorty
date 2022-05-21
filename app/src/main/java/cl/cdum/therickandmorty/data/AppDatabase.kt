package cl.cdum.therickandmorty.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cl.cdum.therickandmorty.api.character.CharacterDetailData
import cl.cdum.therickandmorty.api.character.CharacterListData
import cl.cdum.therickandmorty.data.dao.CharacterDetailDao
import cl.cdum.therickandmorty.data.dao.CharacterListDao
import cl.cdum.therickandmorty.utils.Converters

@Database(
    entities = [CharacterListData::class, CharacterDetailData::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterListDao(): CharacterListDao
    abstract fun characterDetailDao(): CharacterDetailDao
}