package com.example.composepractice.node

import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.composepractice.FrameLayoutAppiler
import com.example.composepractice.FrameLayoutNode

@Composable
fun FrameLayoutPropertyNode(
    @DrawableRes background: Int? = null,
    onClick: () -> Unit
) {
    val frameLayout = (currentComposer.applier as FrameLayoutAppiler).frameLayout

    ComposeNode<FrameLayoutPropertyNode, FrameLayoutAppiler>(
        factory = {
            FrameLayoutPropertyNode(frameLayout)
        },
        update = {
            if(background != null) {
                set(background) {
                    this.frameLayout.background = ContextCompat.getDrawable(this.frameLayout.context, background)
                }
            }
            set(onClick) {
                this.frameLayout.setOnClickListener {
                    onClick.invoke()
                }
            }
        }
    )

}

private class FrameLayoutPropertyNode(
    val frameLayout: FrameLayout
) : FrameLayoutNode {}
