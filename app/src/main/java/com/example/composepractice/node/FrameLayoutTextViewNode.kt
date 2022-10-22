package com.example.composepractice.node

import android.widget.FrameLayout
import android.widget.TextView
import com.example.composepractice.FrameLayoutNode

class FrameLayoutTextViewNode(
    val frameLayout: FrameLayout,
    val textView: TextView
) : FrameLayoutNode {

    override fun onAttached() {
        frameLayout.addView(textView)
    }

    override fun onRemoved() {
        frameLayout.removeView(textView)
    }

    override fun onCleared() {
        frameLayout.removeView(textView)
    }
}
