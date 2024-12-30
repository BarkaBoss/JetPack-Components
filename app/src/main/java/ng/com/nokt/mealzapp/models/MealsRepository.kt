package ng.com.nokt.mealzapp.models

import ng.com.nokt.mealzapp.models.api.MealsWebService
import ng.com.nokt.mealzapp.models.response.MealsCategoriesResponse
import ng.com.nokt.mealzapp.models.response.MealsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    private var cachedMeals = listOf<MealsResponse>()

    suspend fun getMeals(): MealsCategoriesResponse {
        val response = webService.getMeals()
        cachedMeals = response.categories
        return response
    }

    fun getMeal(id: String): MealsResponse? {
        return cachedMeals.firstOrNull {
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance() = instance?: synchronized(this) {
            instance ?: MealsRepository().also { instance = it }
        }
    }
}