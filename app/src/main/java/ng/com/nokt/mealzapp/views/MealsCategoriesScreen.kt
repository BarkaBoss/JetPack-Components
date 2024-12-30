package ng.com.nokt.mealzapp.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import ng.com.nokt.mealzapp.models.response.MealsResponse
import ng.com.nokt.mealzapp.ui.theme.MealzAppTheme

@Composable
fun MealsCategoriesScreen(navigationCallback: (String) -> Unit) {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val meals = viewModel.mealsState.value
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(meals) { meal ->
            MealsCategory(meal = meal, navigationCallback)
        }
    }
}

@Composable
fun MealsCategory(meal: MealsResponse, navigationCallback: (String) -> Unit){
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, end = 5.dp, start = 5.dp)
            .clickable { navigationCallback(meal.id) }
    ){
        Row(
            modifier = Modifier.animateContentSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meal.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Meals Image",
                modifier = Modifier
                    .size(90.dp)
                    .padding(4.dp)
                    .align(
                        Alignment.CenterVertically,
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f)
                    .padding(16.dp)
            ) {
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.headlineSmall
                )
                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(0.6f)) {
                    Text(
                        text = meal.description,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleSmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (isExpanded) meal.description.length else 5
                    )
                }

            }
            Icon(
                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowDown
                    else Icons.Filled.KeyboardArrowUp,
                contentDescription = "Expand Row Icon",
                modifier = Modifier
                    .padding(15.dp)
                    .align(
                        if (isExpanded) Alignment.Bottom
                        else Alignment.CenterVertically)
                    .clickable { isExpanded = !isExpanded },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MealzAppTheme {
        MealsCategoriesScreen({})
    }
}