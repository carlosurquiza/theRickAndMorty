package cl.cdum.therickandmorty.data.repository

import androidx.room.withTransaction
import cl.cdum.therickandmorty.api.ApiService
import cl.cdum.therickandmorty.data.AppDatabase
import cl.cdum.therickandmorty.utils.networkBoundResource
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) {
    private val characterListDao = appDatabase.characterListDao()
    private val characterDetailDao = appDatabase.characterDetailDao()

    fun getCharacterList() = networkBoundResource(
        databaseQuery = {
            characterListDao.getAllCharacterList()
        },
        networkCall = {
            apiService.getCharacterList()
        },
        saveCallResult = {
            appDatabase.withTransaction {
                characterListDao.deleteAllCharacterList()
                characterListDao.insertCharacterList(it)
            }
        }
    )

    fun getCharacterDetail(id: Int) = networkBoundResource(
        databaseQuery = {
            characterDetailDao.getAllCharacterDetail()
        },
        networkCall = {
            apiService.getCharacterDetail(id)
        },
        saveCallResult = {
            appDatabase.withTransaction {
                characterDetailDao.deleteAllCharacterDetail()
                characterDetailDao.insertCharacterDetail(it)
            }
        }
    )
}