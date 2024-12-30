package ng.com.nokt.mealzapp.views

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ng.com.nokt.mealzapp.models.MealsRepository
import ng.com.nokt.mealzapp.models.response.MealsCategoriesResponse
import ng.com.nokt.mealzapp.models.response.MealsResponse

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository()): ViewModel() {


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            mealsState.value = meals
        }
    }
    val mealsState: MutableState<List<MealsResponse>> = mutableStateOf(emptyList<MealsResponse>())


    private suspend fun getMeals(): List<MealsResponse> {
        return repository.getMeals().categories
    }
}