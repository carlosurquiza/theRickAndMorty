package cl.cdum.therickandmorty.utils

import androidx.room.TypeConverter
import cl.cdum.therickandmorty.api.character.Info
import cl.cdum.therickandmorty.api.character.Location
import cl.cdum.therickandmorty.api.character.Origin
import cl.cdum.therickandmorty.api.character.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun restoreResult(objectToRestore: String?): List<Result>? {
        return Gson().fromJson(
            objectToRestore,
            object : TypeToken<List<Result>?>() {}.type
        )
    }

    @TypeConverter
    fun saveResult(objectToSave: List<Result>?): String? {
        return Gson().toJson(objectToSave)
    }

    @TypeConverter
    fun restoreString(objectToRestore: String?): List<String>? {
        return Gson().fromJson(
            objectToRestore,
            object : TypeToken<List<String>?>() {}.type
        )
    }

    @TypeConverter
    fun saveString(objectToSave: List<String>?): String? {
        return Gson().toJson(objectToSave)
    }

    @TypeConverter
    fun restoreLocation(objectToRestore: String?): Location? {
        return Gson().fromJson(
            objectToRestore,
            object : TypeToken<Location?>() {}.type
        )
    }

    @TypeConverter
    fun saveLocation(objectToSave: Location?): String? {
        return Gson().toJson(objectToSave)
    }

    @TypeConverter
    fun restoreOrigin(objectToRestore: String?): Origin? {
        return Gson().fromJson(
            objectToRestore,
            object : TypeToken<Origin?>() {}.type
        )
    }

    @TypeConverter
    fun saveOrigin(objectToSave: Origin?): String? {
        return Gson().toJson(objectToSave)
    }

    @TypeConverter
    fun restoreInfo(objectToRestore: String?): Info? {
        return Gson().fromJson(
            objectToRestore,
            object : TypeToken<Info?>() {}.type
        )
    }

    @TypeConverter
    fun saveInfo(objectToSave: Info?): String? {
        return Gson().toJson(objectToSave)
    }
}