package dev.fizcode.mofiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.fizcode.mofiz.ui.components.HomeMovieLazyRow
import dev.fizcode.mofiz.ui.screens.home.HomeViewModel
import dev.fizcode.mofiz.ui.theme.MoFizMovieDataListsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoFizMovieDataListsTheme {
                val homeViewModel: HomeViewModel = hiltViewModel()
                // Bind viewModel
                homeViewModel.onViewLoaded()
                val nowPlaying = homeViewModel.shouldShowNowPlaying.collectAsState()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("This is Test Activity Only!!!")
                    HomeMovieLazyRow(
                        cardHeader = "Now Playing 🍿",
                        items = nowPlaying.value.results
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello, $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoFizMovieDataListsTheme {
        Greeting("Android")
    }
}