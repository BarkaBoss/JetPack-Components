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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import ng.com.nokt.mealzapp.models.response.MealsResponse

@Composable
fun MealDetailsScreen(meal: MealsResponse?) {
    var mealPictureState by remember {
        mutableStateOf(MealItemProfileImage.Normal)
    }
    val transition = updateTransition(targetState = mealPictureState, label = "")
    val imageSizeDp by transition.animateDp (targetValueByState = { it.size }, label = "")
    val color by transition.animateColor(targetValueByState = { it.color }, label = "")
    val borderWidth by transition.animateDp (targetValueByState = {it.borderWidth}, label = "")
    Column {
        Row {
            Card(
                modifier = Modifier.padding(16.dp),
                shape = CircleShape,
                border = BorderStroke(width = borderWidth, color = color)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(meal?.imageUrl)
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build(),
                    contentDescription = "Meals Image",
                    modifier = Modifier
                        .size(imageSizeDp)
                        .padding(8.dp)
                )
            }
            Text(
                meal?.name?: "default name",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically),
                )
        }
        Button(
            modifier = Modifier
                .padding(top = 16.dp, end = 20.dp, start = 20.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            onClick = {
                mealPictureState = if (mealPictureState ==  MealItemProfileImage.Normal)
                    MealItemProfileImage.Expanded
                else
                    MealItemProfileImage.Normal
            }
        ) {
            Text("Change state of meal profile picture")
        }
    }
}

enum class MealItemProfileImage(val color: Color, val size: Dp, val borderWidth: Dp){
    Normal(Color.Red, 120.dp, 2.dp),
    Expanded(Color.Green, 200.dp, 5.dp)
}