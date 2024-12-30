package ng.com.nokt.mealzapp.views

import androidx.lifecycle.ViewModel
import ng.com.nokt.mealzapp.models.MealsRepository
import ng.com.nokt.mealzapp.models.response.MealsCategoriesResponse

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository()): ViewModel() {
    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {
        repository.getMeals { response ->
            successCallback(response)
        }
    }
}