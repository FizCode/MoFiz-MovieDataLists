package dev.fizcode.mofiz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dev.fizcode.mofiz.R
import dev.fizcode.mofiz.common.Constant.Named.IMAGE_URL
import dev.fizcode.mofiz.data.api.MovieListResponse
import dev.fizcode.mofiz.ui.navigation.navgraph.Screen

@Composable
fun MovieListCardsSmall(
    movieTitle: String,
    moviePoster: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .size(width = 98.dp, height = Dp.Unspecified)
            .clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(4.dp),

        ) {
        Card(
            modifier = Modifier.size(width = 98.dp, height = 140.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = moviePoster,
                    contentDescription = "Movie Poster",
                    placeholder = painterResource(id = R.drawable.loading_image_small100x144),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Text(
            text = movieTitle,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun MovieListCardsBig(
    movieTitle: String,
    movieBackdrop: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .size(width = 212.dp, height = 176.dp)
            .clickable { onClick() }
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium
            ),
    ) {
        Card(modifier = Modifier.size(width = 212.dp, height = 120.dp)) {
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = movieBackdrop,
                    contentDescription = "Movie Backdrop",
                    placeholder = painterResource(id = R.drawable.loading_image_big212x120),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = movieTitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun PosterPath(
    moviePoster: String
) {
    Card(
        modifier = Modifier.size(width = 98.dp, height = 140.dp)
    ) {
        Box(
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = moviePoster,
                contentDescription = "Movie Poster",
                placeholder = painterResource(id = R.drawable.loading_image_small100x144),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

    }
}

// Lazy Columns & Rows
@Composable
fun HomeMovieSmallLazyRow(
    cardHeader: String,
    items: ArrayList<MovieListResponse.Results>,
    navController: NavController
) {
    Column(
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Text(
                text = cardHeader,
                style = MaterialTheme.typography.headlineSmall
            )
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            itemsIndexed(
                items = items.take(8)
            ) { _, item ->
                item.title?.let { title ->
                    item.posterPath?.let { poster ->
                        MovieListCardsSmall(
                            movieTitle = title,
                            moviePoster = IMAGE_URL + poster,
                            onClick = {
                                item.id?.let {
                                    navController.navigate(route = Screen.Details.passId(it))
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeMovieBigLazyRow(
    cardHeader: String,
    items: ArrayList<MovieListResponse.Results>,
    navController: NavController
) {
    Column(
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Text(
                text = cardHeader,
                style = MaterialTheme.typography.headlineSmall
            )
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            itemsIndexed(
                items = items.take(5)
            ) { _, item ->
                item.title?.let { title ->
                    item.backdropPath?.let { backdrop ->
                        MovieListCardsBig(
                            movieTitle = title,
                            movieBackdrop = IMAGE_URL + backdrop,
                            onClick = {
                                item.id?.let {
                                    navController.navigate(route = Screen.Details.passId(it))
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardsLightPreview() {
    MovieListCardsBig(movieTitle = "Lorem", movieBackdrop = "Lorem") {

    }
}