package com.carsense.ai.ui.profile.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carsense.ai.ui.theme.onSurfaceVariantDark

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SectionHeader(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    iconColor: Color = MaterialTheme.colorScheme.primaryContainer
) {
    Row(
        modifier = Modifier.padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(20.dp)
        )
        Column (){
            Text(
                text = title.uppercase(),
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,

            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    modifier = Modifier.width(140.dp).basicMarquee(
                        iterations = Int.MAX_VALUE, // infinite loop
                        animationMode = MarqueeAnimationMode.Immediately,
                        initialDelayMillis = 1000, // pause before start
                    ),
                    color = onSurfaceVariantDark,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
