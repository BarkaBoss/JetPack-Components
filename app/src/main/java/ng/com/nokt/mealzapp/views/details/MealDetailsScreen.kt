package ng.com.nokt.mealzapp.views

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import ng.com.nokt.mealzapp.models.response.MealsResponse
import java.lang.Float.min

@Composable
fun MealDetailsScreen(meal: MealsResponse?) {
    val scrollState = rememberLazyListState()
    val offset = min(1f, 1 -
            (scrollState.firstVisibleItemScrollOffset / 600f
                    + scrollState.firstVisibleItemIndex))
    val size by animateDpAsState(targetValue = max(100.dp, 200.dp * offset))

    Surface(
        color = MaterialTheme.colorScheme.background,
        ) {
        Column(modifier = Modifier.padding(top = 15.dp)) {
            Surface(shadowElevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.Green
                        )
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(meal?.imageUrl)
                                .crossfade(true)
                                .transformations(CircleCropTransformation())
                                .build(),
                            contentDescription = "Meals Image",
                            modifier = Modifier
                                .size(size)
                                .padding(4.dp)
                        )

                    }
                    Text(
                        text = meal?.name ?: "default name",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            val dummyList = (0..100).map { it.toString() }
            LazyColumn(state = scrollState) {
                items(dummyList){ dummyItem->
                    Text(text = dummyItem, modifier = Modifier.padding(24.dp))
                }
            }
        }
    }
}

enum class MealItemProfileImage(val color: Color, val size: Dp, val borderWidth: Dp){
    Normal(Color.Red, 120.dp, 2.dp),
    Expanded(Color.Green, 200.dp, 5.dp)
}