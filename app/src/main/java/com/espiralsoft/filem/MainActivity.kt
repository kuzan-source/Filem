/*
 * Copyright (c) 2025 Jose Luis Vargas Ibarra
 *
 * This file is part of Filem.
 *
 * Filem is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Filem is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.espiralsoft.filem

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.espiralsoft.filem.navigation.AppNavigation
import com.espiralsoft.filem.presentation.ui.theme.FilemTheme
import com.espiralsoft.filem.utils.PermissionUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!PermissionUtils.hasPermissions()) {
            PermissionUtils.requestAllFilesAccessPermission(this)
        }

        setContent {
            FilemTheme {
                AppNavigation()
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()

        if (!PermissionUtils.hasPermissions()) {
            Toast.makeText(this, "Se requieren permisos para usar la app", Toast.LENGTH_LONG).show()
        }

    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
