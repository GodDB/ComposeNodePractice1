package com.example.composepractice

import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.platform.LocalContext
import com.example.composepractice.node.FrameLayoutTextViewNode

@Composable
fun FrameLayoutTextView(
    text: String,
    onClick: () -> Unit
) {
    val frameLayout = (currentComposer.applier as FrameLayoutAppiler).frameLayout
    val context = LocalContext.current

    ComposeNode<FrameLayoutTextViewNode, FrameLayoutAppiler>(
        factory = {
            FrameLayoutTextViewNode(frameLayout, TextView(context))
        },
        update = {
            set(text) {
                this.textView.text = text
            }
            set(onClick) {
                this.textView.setOnClickListener {
                    onClick.invoke()
                }
            }
        }
    )

}


