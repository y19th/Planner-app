package com.example.util.extension

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun vector(@DrawableRes res: Int): ImageVector {
    return ImageVector.vectorResource(id = res)
}