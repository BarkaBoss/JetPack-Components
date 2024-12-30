package ng.com.nokt.mealzapp.models

import ng.com.nokt.mealzapp.models.api.MealsWebService
import ng.com.nokt.mealzapp.models.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    suspend fun getMeals(): MealsCategoriesResponse {
        return webService.getMeals()
    }
}