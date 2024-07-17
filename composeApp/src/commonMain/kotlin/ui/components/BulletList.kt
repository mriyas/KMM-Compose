package ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import models.Reviews

@Composable
internal fun ReviewsList(
    title: String,
    items: List<Reviews>,
    modifier: Modifier = Modifier,
    indent: Dp = 20.dp,
    lineSpacing: Dp = 0.dp,
) {
    Column(modifier = modifier) {

        Text(
            modifier = Modifier.padding(2.dp),
            text = "$title (${items.size})",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.subtitle1,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(5.dp))
        items.forEach { product ->
            var rating = ""
            repeat(product.rating) {
                rating += "⭐"
            }
            Text(
                text = rating,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.caption,
                color = Color.Black,
            )
            Text(
                text = product.comment,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.caption,
                color = Color.Black,
            )
            Row {
                Text(
                    text = product.reviewerName,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.caption,
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.width(6.dp))
                val formattedDateString = Platform.dateFormatter.run { product.date.toFormattedDateString() }
                Text(
                    text = formattedDateString,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.caption,
                    color = Color.Gray,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

