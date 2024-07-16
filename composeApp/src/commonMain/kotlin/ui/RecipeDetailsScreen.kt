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
import models.Recipe
import ui.components.BulletList
import ui.components.RecipeTags

@Composable
fun RecipeDetailsScreen(
    recipe: Recipe,
    onDismiss: () -> Unit,
) {
   // val recipe = recipeState?.value ?: return
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
                        resource = asyncPainterResource(data = recipe.image),
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
                        text = recipe.name,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6,
                        color = Color.Black,
                    )
                    RecipeTags(recipe.tags)
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = "Preparation Time: ${recipe.prepTimeMinutes} Mnts",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.caption,
                        color = Color.Gray,
                    )
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = "Cooking Time: ${recipe.cookTimeMinutes} Mnts",
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.caption,
                        color = Color.Gray,
                    )
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = "Level: ${recipe.difficulty}",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.button,
                        color = Color(red = 0.1f, green = 0.8f, blue = 0.0f)
                    )

                    BulletList(
                        title = "Ingredients",
                        items = recipe.ingredients,
                        lineSpacing = 8.dp,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BulletList(
                        title = "Instructions",
                        items = recipe.instructions,
                        lineSpacing = 8.dp,
                    )
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}

