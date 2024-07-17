package ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import models.Product
import ui.components.RecipeTags
import ui.components.ReviewsList

@Composable
fun RecipeDetailsScreen(
    product: Product,
    onDismiss: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 300.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn {
            item {
                Box(contentAlignment = Alignment.TopEnd) {

                    KamelImage(
                        resource = asyncPainterResource(data = product.images[0]),
                        contentDescription = null,
                        modifier = Modifier.height(300.dp).fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop,
                        animationSpec = tween(),
                        onLoading = { progress -> CircularProgressIndicator(progress) },
                    )
                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp)
                            .alpha(.8f)
                            .clip(CircleShape)
                            .background(Color.White)
                            .clickable(
                                role = Role.Button,
                                onClick = onDismiss,
                                enabled = true
                            ),
                        tint = Color.Black,
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close",

                        )
                }

            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = product.title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6,
                        color = Color.Black,
                    )
                    RecipeTags(product.tags)
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = "Price: ${product.price} USD",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.caption,
                        color = Color.Gray,
                    )
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = "Discount: ${product.discountPercentage} USD",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.caption,
                        color = Color.Gray,
                    )
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = "Stock Available: ${product.stock}",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.button,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = "Description: ${product.description}",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.button,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ReviewsList(
                        title = "Ratings",
                        items = product.reviews,
                        lineSpacing = 8.dp,
                    )
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}

