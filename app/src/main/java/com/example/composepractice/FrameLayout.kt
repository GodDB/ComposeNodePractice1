package com.example.composepractice

import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.Composition
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.composepractice.node.FrameLayoutPropertyNode
import kotlinx.coroutines.awaitCancellation

@Composable
fun FrameLayout(
    @DrawableRes background: Int? = null,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val frameLayout = remember {
        FrameLayout(context)
    }
    AndroidView(
        factory = { frameLayout }
    )

    val parentComposeContext = rememberCompositionContext()
    val onClickState = rememberUpdatedState(newValue = onClick)
    val backgroundState = rememberUpdatedState(newValue = background)
    val contentState = rememberUpdatedState(newValue = content)

    LaunchedEffect(key1 = Unit) {
        disposingComposition(
            compositionFactory = {
                Composition(
                    applier = FrameLayoutAppiler(frameLayout),
                    parent = parentComposeContext
                ).apply {
                    setContent {
                        FrameLayoutPropertyNode(
                            background = backgroundState.value,
                            onClick = onClickState.value
                        )
                        contentState.value.invoke()
                    }
                }
            }
        )
    }
}



private suspend fun disposingComposition(compositionFactory: () -> Composition) {
    val newComposition = compositionFactory.invoke()
    try {
        awaitCancellation()
    } finally {
        newComposition.dispose()
    }
}
