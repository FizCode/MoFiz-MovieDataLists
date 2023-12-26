package dev.fizcode.mofiz.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dev.fizcode.mofiz.R
import dev.fizcode.mofiz.common.Constant.Named.IMAGE_URL
import dev.fizcode.mofiz.ui.components.ImageCard
import dev.fizcode.mofiz.ui.components.TagTextOnly

@Composable
fun DetailsScreen(
    navController: NavController,
    argsId: Int
) {

    val detailsViewModel: DetailsViewModel = hiltViewModel()

    // Bind vieModel
    detailsViewModel.onViewLoaded(movieId = argsId)
    val movieDetails = detailsViewModel.shouldShowDetails.collectAsState()

    Scaffold() {
        innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                AsyncImage(
                    model = IMAGE_URL + movieDetails.value.backdropPath,
                    placeholder = painterResource(R.drawable.loading_image100x144),
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .aspectRatio(16f / 9f),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(78.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp)
                    )
                ) { /* BLANK */ }
                // Poster, Rating, Bookmark Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Card(
                        modifier = Modifier.size(width = 98.dp, height = 140.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .height(140.dp)
                                .fillMaxWidth()
                        ) {
                            ImageCard(moviePoster = IMAGE_URL + movieDetails.value.posterPath)
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val apiRating = movieDetails.value.voteAverage?.toInt()

                        Column(
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(32.dp)
                                ),
                        ) {
                            Row (
                                Modifier
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Icon(
                                    imageVector = Icons.Rounded.StarRate,
                                    contentDescription = "Start Rating",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                                Spacer(modifier = Modifier.size(4.dp))
                                Text(
                                    text = "$apiRating",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(
                            onClick = { /* TODO */ },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.BookmarkBorder,
                                contentDescription = "Bookmark"
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(text = "Bookmark")
                        }
                    }
                }
            }

            // Movie Type, Title, Genre
            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        itemsIndexed(items = movieDetails.value.productionCountries) { _, item ->
                            Row(
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colorScheme.tertiary,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                            ) {
                                Text(
                                    text = "${item.iso31661}",
                                    modifier = Modifier.padding(8.dp),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onTertiary
                                )
                            }
                        }
                    }
                    Text(
                        text = "• ${movieDetails.value.runtime}m",
                        modifier = Modifier.padding(horizontal = 8.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Text(
                    text = "${ movieDetails.value.originalTitle }",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    itemsIndexed(
                        items = movieDetails.value.genres
                    ) { _, item ->
                        TagTextOnly(tagText = "${ item.name }")
                    }
                }
            }

            // Overviews
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Overview",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "${movieDetails.value.overview}",
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}