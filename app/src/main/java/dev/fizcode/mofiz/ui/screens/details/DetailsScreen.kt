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
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dev.fizcode.mofiz.R
import dev.fizcode.mofiz.common.Constant.Named.IMAGE_URL
import dev.fizcode.mofiz.ui.components.PosterPath
import dev.fizcode.mofiz.ui.components.TagTextOnly

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    argsId: Int
) {

    // Remember State
    var isBookmarked by remember { mutableStateOf(false) }

    // Bind vieModel
    val detailsViewModel: DetailsViewModel = hiltViewModel()
    val movieDetails = detailsViewModel.shouldShowDetails.collectAsState()
    detailsViewModel.onViewLoaded(movieId = argsId)
    isBookmarked = detailsViewModel.isBookmarked.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { /* No Title */ },
                colors = topAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    IconButton(
                        colors = IconButtonDefaults
                            .iconButtonColors(MaterialTheme.colorScheme.background),
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                AsyncImage(
                    model = IMAGE_URL + movieDetails.value.backdropPath,
                    placeholder = painterResource(R.drawable.loading_image_small100x144),
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .aspectRatio(16f / 10f),
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
                        .padding(horizontal = 16.dp, vertical = 8.dp),
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
                            PosterPath(moviePoster = IMAGE_URL + movieDetails.value.posterPath)
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val apiRating = movieDetails.value.voteAverage?.toInt()

                        // Ratings
                        Column(
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.tertiary,
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
                                    tint = MaterialTheme.colorScheme.onTertiary
                                )
                                Spacer(modifier = Modifier.size(4.dp))
                                Text(
                                    text = "$apiRating",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.onTertiary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(8.dp))

                        // Bookmark Button
                        if (!isBookmarked) {
                            Button(
                                onClick = { detailsViewModel.bookmarking() },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.BookmarkBorder,
                                    contentDescription = "Bookmark"
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text( text = "Bookmark")
                            }
                        } else {
                            Button(
                                onClick = { detailsViewModel.deleteBookmark() },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Bookmark,
                                    contentDescription = "Remove"
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(text = "Bookmark")
                            }
                        }
                    }
                }
            }

            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Movie Type or Country
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

                    // Movie Duration
                    Text(
                        text = "• ${movieDetails.value.runtime}m",
                        modifier = Modifier.padding(horizontal = 8.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                // Movie Title
                Text(
                    text = "${ movieDetails.value.originalTitle }",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.headlineSmall
                )

                // Movie Genre
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
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
                modifier = Modifier.padding(horizontal = 16.dp),
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