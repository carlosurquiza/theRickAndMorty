package cl.cdum.therickandmorty.api

import cl.cdum.therickandmorty.api.character.CharacterDetailData
import cl.cdum.therickandmorty.api.character.CharacterListData
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val CHARACTER_LIST = "character"
        const val CHARACTER_DETAIL = "character/{id}"
    }

    @GET(CHARACTER_LIST)
    suspend fun getCharacterList(): CharacterListData

    @GET(CHARACTER_DETAIL)
    suspend fun getCharacterDetail(
        @Path("id") id: Int = 0
    ): CharacterDetailData
}