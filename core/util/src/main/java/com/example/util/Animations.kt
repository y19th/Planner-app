package com.example.util

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

object Animations {

    val slideInWithFadeFromRight = run {
        slideInHorizontally(
            animationSpec = tween(durationMillis = AnimationDuration.Short, easing = EaseIn),
            initialOffsetX = { it }
        ) + fadeIn(
            animationSpec = tween(durationMillis = AnimationDuration.Short,easing = LinearEasing)
        )
    }
    val slideInWithFadeFromLeft = run {
        slideInHorizontally(
            animationSpec = tween(durationMillis = AnimationDuration.Short, easing = EaseIn),
            initialOffsetX = { -it }
        ) + fadeIn(
            animationSpec = tween(durationMillis = AnimationDuration.Short,easing = LinearEasing)
        )
    }

    val slideInWithFadeFromBottom = run {
        slideInVertically(
            animationSpec = tween(durationMillis = AnimationDuration.Short, easing = EaseIn),
            initialOffsetY = { it }
        ) + fadeIn(
            animationSpec = tween(durationMillis = AnimationDuration.Short,easing = LinearEasing)
        )
    }

    val slideOutWithFadeToRight = run {
        slideOutHorizontally(
            animationSpec = tween(durationMillis = AnimationDuration.Fast, easing = EaseOut),
            targetOffsetX = { it / 2 }
        ) + fadeOut(
            animationSpec = tween(durationMillis = AnimationDuration.Short,easing = LinearEasing)
        )
    }

    val slideOutWithFadeToLeft = run {
        slideOutHorizontally(
            animationSpec = tween(durationMillis = AnimationDuration.Fast, easing = EaseOut),
            targetOffsetX = { -(it / 2) }
        ) + fadeOut(
            animationSpec = tween(durationMillis = AnimationDuration.Short,easing = LinearEasing)
        )
    }

    val mediumFadeIn = run {
        fadeIn(animationSpec = tween(
            durationMillis = AnimationDuration.Medium,
            easing = LinearEasing
        ))
    }

    val mediumFadeOut = run {
        fadeOut(animationSpec = tween(
            durationMillis = AnimationDuration.Medium,
            easing = LinearEasing
        ))
    }

    val fastFadeOut = run {
        fadeOut(animationSpec = tween(
            durationMillis = AnimationDuration.Fast,
            easing = LinearEasing
        ))
    }

    val shortFadeIn = run {
        fadeIn(animationSpec = tween(
            durationMillis = AnimationDuration.Short,
            easing = LinearEasing
        ))
    }
}


@Suppress("unused")
@Immutable
object AnimationDuration {

    @Stable
    val Fast = 100

    @Stable
    val Short = 200

    @Stable
    val LessMedium = 300

    @Stable
    val Medium = 400

    @Stable
    val LessLong = 500

    @Stable
    val Long = 600
}