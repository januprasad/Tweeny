package com.github.tweeny.ui

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.asImageBitmap

sealed class ImageType
class DrawableResource(val id: Int) : ImageType()
class BitmapResource(val bitmap: Bitmap) : ImageType()

@Composable
fun convert(id: Int) = ImageBitmap.imageResource(id = id)

@Composable
fun FlagImage(
    imageType: ImageType,
    modifier: Modifier = Modifier
        .width(200.dp)
        .height(200.dp)
        .padding(horizontal = 0.dp)
) {
    var bitmap: ImageBitmap? = null
    bitmap = when (imageType) {
        is BitmapResource -> {
            imageType.bitmap.asImageBitmap()
        }
        is DrawableResource -> {
            convert(imageType.id)
        }
    }

    Image(
        bitmap = bitmap,
        contentDescription = "country_flag",
        modifier = modifier,
        alignment = Alignment.BottomStart
    )
}
