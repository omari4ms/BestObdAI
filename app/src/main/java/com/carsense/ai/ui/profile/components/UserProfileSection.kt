package com.carsense.ai.ui.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.carsense.ai.ui.theme.*

@Composable
fun UserProfileSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Avatar with Glow
        Box(
            modifier = Modifier.padding(bottom = 24.dp, top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            // Glow effect
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .blur(40.dp)
                    .background(primaryContainerDark.copy(alpha = 0.4f), CircleShape)
            )
            
            // Avatar
            AsyncImage(
                model = "https://lh3.googleusercontent.com/aida/ADBb0uj3torUrx6Y6ynqUnBlFQlUzngH6sqQ174rCwtatutOsIEuNkomx3VuyE7F4A7XbHGXDzG0k3KpZtaHdy7mNaeS9QtfCwv_rnzZfuyW0p054NGPga46zXwNGfKFBkZ2yyPa6pp4v3HeDsGNhBp5zTX6gusc3x89wZMaRk-cfY1kC8Z5RDP62nHmsi4mp7cw2S1kX_9zBDIbhRriNeQ8CS4xg2ghLw6jqRpODV9vDQlfrnO1HlgJwPjstmWHmdd7_nD5LNnzrRfL4uk",
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(surfaceContainerLow)
                    .border(2.dp, primaryContainerDark, CircleShape)
                    .padding(4.dp)
                    .clip(CircleShape)
            )
        }

        // Name & Badge
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Text(
                text = "Simmo",
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedButton(
                onClick = { /* TODO */ },
                shape = CircleShape,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = surfaceContainerHighest.copy(alpha = 0.4f),
                    contentColor = primaryContainerDark
                ),
                border = BorderStroke(1.dp, primaryContainerDark.copy(alpha = 0.3f)),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                modifier = Modifier.height(32.dp)
            ) {
                Text(
                    text = "MODIFY",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            }
        }

        // Membership Badge
        Surface(
            color = surfaceContainerHighest.copy(alpha = 0.4f),
            shape = CircleShape,
            border = BorderStroke(1.dp, primaryContainerDark.copy(alpha = 0.2f)),
            modifier = Modifier.padding(bottom = 48.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = primaryContainerDark,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "MEMBERSHIP: PRO ELITE",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = primaryContainerDark,
                    letterSpacing = 1.5.sp
                )
            }
        }

        // Stats Grid
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem(
                icon = Icons.Outlined.LocationOn,
                iconColor = secondaryDark,
                value = "1,242",
                label = "TRIPS"
            )
            StatItem(
                icon = Icons.Outlined.Security,
                iconColor = primaryContainerDark,
                value = "98",
                label = "SAFETY SCORE"
            )
            StatItem(
                icon = Icons.Outlined.DateRange,
                iconColor = secondaryDark,
                value = "2022",
                label = "MEMBER SINCE"
            )
        }

        // Vehicle Information Separator
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color.Transparent, primaryContainerDark.copy(alpha = 0.3f))
                        )
                    )
            )
            Text(
                text = "VEHICLE INFORMATION",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = primaryContainerDark,
                letterSpacing = 2.sp,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(primaryContainerDark.copy(alpha = 0.3f), Color.Transparent)
                        )
                    )
            )
        }

        // Vehicle Info Card
        Surface(
            color = surfaceContainerHighest.copy(alpha = 0.4f),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.05f)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement  = Arrangement.spacedBy(10.dp)
            ) {
                VehicleInfoItem("MODEL", "Model S Plaid", Color.White)
                VehicleInfoItem("YEAR", "2023", Color.White)
                VehicleInfoItem("VIN", "5YRSA1E4XPF012345", secondaryDark)
            }
        }
    }
}

@Composable
private fun StatItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    value: String,
    label: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier
                .size(24.dp)
                .padding(bottom = 8.dp)
        )
        Text(
            text = value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = label,
            fontSize = 10.sp,
            color = onSurfaceVariantDark.copy(alpha = 0.6f),
            letterSpacing = 1.5.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
private fun VehicleInfoItem(
    label: String,
    value: String,
    valueColor: Color
) {
    Column {
        Text(
            text = label,
            fontSize = 10.sp,
            color = onSurfaceVariantDark.copy(alpha = 0.5f),
            letterSpacing = 1.5.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = valueColor,
            letterSpacing = if (label == "VIN") 1.5.sp else 0.sp
        )
    }
}
