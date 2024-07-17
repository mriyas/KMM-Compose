package ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch
import models.Product
import ui.RecipeDetailsScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ProductsList(list: List<Product>) {
    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    val scope = rememberCoroutineScope()
    val selectedData = mutableStateOf<Product>(list[0])

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            RecipeDetailsScreen(selectedData.value) {
                    scope.launch {
                        //  selectedData.value = null
                        bottomSheetState.collapse()
                    }
                }
        },
        sheetPeekHeight = 0.dp,
        drawerElevation = 10.dp,
        sheetShape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
        sheetElevation = 20.dp,

        ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    contentColor = Color.Black,
                    backgroundColor = Color.White,
                    title = {
                        Text(
                            "Home",
                            maxLines = 1,
                        )
                    }
                )
            },
        ) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalItemSpacing = 5.dp,
                modifier = Modifier.fillMaxSize(),
                content = {
                    items(list.size) { index ->
                        ProductItem(list[index]) {
                            scope.launch {
                                selectedData.value = list[index]
                                bottomSheetState.expand()
                            }
                        }
                    }
                }
            )
        }
    }


}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface,
        onClick = {
            onClick()
        },
    ) {

        Column(modifier = Modifier.padding(10.dp)) {
            KamelImage(
                resource = asyncPainterResource(data = product.images[0]),
                contentDescription = null,
                modifier = Modifier.height(150.dp).fillMaxWidth().clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop,
                animationSpec = tween(),
                onLoading = { progress -> CircularProgressIndicator(progress) },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = product.title,
                    maxLines = 2,
                    minLines = 2,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "Preparation Time: ${product.price} Mnts",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.caption,
                    color = Color.Gray,
                )
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "Cooking Time: ${product.discountPercentage} Mnts",
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.caption,
                    color = Color.Gray,
                )
                RecipeTags(product.tags)
            }
        }
    }
}