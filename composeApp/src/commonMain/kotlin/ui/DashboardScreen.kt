package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.getKoin
import ui.components.RecipeCard
import viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(){

    val viewModel: DashboardViewModel= getKoin().get()
    val homeScreenState by viewModel.homeViewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }
    when (homeScreenState) {
        is DashboardViewModel.HomeScreenState.Loading -> {
            ProgressIndicator()
        }
        is DashboardViewModel.HomeScreenState.Success -> {
            val products = (homeScreenState as DashboardViewModel.HomeScreenState.Success).responseData.list
            RecipeCard(products)
        }
        is DashboardViewModel.HomeScreenState.Error -> {
           //show Error
        }
    }
}

