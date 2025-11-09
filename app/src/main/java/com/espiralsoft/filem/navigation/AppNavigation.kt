/**
 * Manejo de navegación de la aplicación
 */
package com.espiralsoft.filem.navigation

import android.net.Uri
import com.espiralsoft.filem.presentation.ui.screens.FileListHubScreen
import java.nio.file.Paths
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.espiralsoft.filem.presentation.ui.screens.FileListScreen
import com.espiralsoft.filem.utils.NavigationUtility
import com.espiralsoft.filem.utils.NavigationUtility.Explorer.createRoute
import com.espiralsoft.filem.utils.NavigationUtility.getRootPath
import java.nio.file.Path

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val rootPath = getRootPath()

    NavHost(
        navController = navController,
        startDestination = NavigationUtility.Hub.ROUTE
    ) {
        composable(NavigationUtility.Hub.ROUTE) {
            FileListHubScreen(
                currentPath = rootPath,
                onNavigateTo = { path ->
                    navController.navigate(createRoute(path.toString()))
                }
            )
        }

        composable(
            route = NavigationUtility.Explorer.ROUTE,
            arguments = NavigationUtility.Explorer.navArguments()
        ) { backStackEntry ->

            val encodedPath = backStackEntry.arguments?.getString("encodedPath")
            val path = Paths.get(Uri.decode(encodedPath ?: rootPath.toString()))

            FileListScreen(
                initialPath = path,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}