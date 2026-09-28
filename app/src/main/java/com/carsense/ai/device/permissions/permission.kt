package com.carsense.ai.device.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

/**
 * Returns a list of required runtime permissions based on the device's Android version.
 * Note: INTERNET is an install-time permission and does not need runtime request, 
 * but must be in AndroidManifest.xml.
 */
fun getRequiredPermissions(): List<String> {
    val permissions = mutableListOf<String>()

    // Notification (Android 13+)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        permissions.add(Manifest.permission.POST_NOTIFICATIONS)
    }

    // Bluetooth (Android 12+)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        permissions.add(Manifest.permission.BLUETOOTH_CONNECT)
        permissions.add(Manifest.permission.BLUETOOTH_SCAN)
    }

    // Location (Often required for WiFi scanning)
    permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
    permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)

    // Storage
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        permissions.add(Manifest.permission.READ_MEDIA_IMAGES)
        permissions.add(Manifest.permission.READ_MEDIA_VIDEO)
        permissions.add(Manifest.permission.READ_MEDIA_AUDIO)
    } else {
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    return permissions
}

fun checkAllPermissionsGranted(context: Context, permissions: List<String>): Boolean {
    return permissions.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }
}

@Composable
fun AppPermissionsHandler(
    onAllPermissionsGranted: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as? Activity
    
    val requiredPermissions = remember { getRequiredPermissions() }
    
    var showSettingsDialog by remember { mutableStateOf(false) }
    var permissionsToRequest by remember { mutableStateOf(requiredPermissions) }

    // SharedPreferences to track how many times we've asked
    val prefs = remember { context.getSharedPreferences("PermissionPrefs", Context.MODE_PRIVATE) }
    
    var showRetryDialog by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val allGranted = permissionsMap.values.all { it }
        if (allGranted) {
            onAllPermissionsGranted()
        } else {
            // Count denials
            var maxDenials = 0
            permissionsMap.forEach { (perm, granted) ->
                if (!granted) {
                    val denyCount = prefs.getInt(perm, 0) + 1
                    prefs.edit().putInt(perm, denyCount).apply()
                    if (denyCount > maxDenials) {
                        maxDenials = denyCount
                    }
                }
            }

            // If denied 2 or more times, prompt to go to settings
            if (maxDenials >= 2) {
                showSettingsDialog = true
            } else if (maxDenials == 1) {
                showRetryDialog = true
            }
        }
    }

    LaunchedEffect(Unit) {
        if (checkAllPermissionsGranted(context, requiredPermissions)) {
            onAllPermissionsGranted()
        } else {
            val ungrantedPermissions = requiredPermissions.filter {
                ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
            }
            permissionLauncher.launch(ungrantedPermissions.toTypedArray())
        }
    }

    if (showSettingsDialog) {
        AlertDialog(
            onDismissRequest = { showSettingsDialog = false },
            title = { Text("Permissions Required") },
            text = { Text("You have denied necessary permissions multiple times. The app requires Bluetooth, WiFi, Storage, and Notification access to function properly. Please enable them manually in the app settings.") },
            confirmButton = {
                Button(onClick = {
                    showSettingsDialog = false
                    openAppSettings(context)
                }) {
                    Text("Go to Settings")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSettingsDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showRetryDialog) {
        AlertDialog(
            onDismissRequest = { showRetryDialog = false },
            title = { Text("Permissions Needed") },
            text = { Text("These permissions are required for the app to function properly. Please grant them to continue.") },
            confirmButton = {
                Button(onClick = {
                    showRetryDialog = false
                    val ungrantedPermissions = requiredPermissions.filter {
                        ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
                    }
                    permissionLauncher.launch(ungrantedPermissions.toTypedArray())
                }) {
                    Text("Try Again")
                }
            },
            dismissButton = {
                TextButton(onClick = { showRetryDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

fun openAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
    }
    context.startActivity(intent)
}
