package uiwithlogic.inside.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.RouteBuilder
import uiwithlogic.inside.home.ui.HomeScreen

fun RouteBuilder.homeRoute(
    group: String,
) {
    group(route = group, initialRoute = HomeDestination.HomeScreen.name) {
        scene(HomeDestination.HomeScreen.name){
            Column(modifier = Modifier.padding(top = 60.dp)) {
                HomeScreen()
            }
        }
    }
}