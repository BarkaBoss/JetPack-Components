package ng.com.nokt.mealzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ng.com.nokt.mealzapp.ui.theme.MealzAppTheme
import ng.com.nokt.mealzapp.views.MealsCategoriesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealzAppTheme {
                MealsCategoriesScreen()
            }
        }
    }
}