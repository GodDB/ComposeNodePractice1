package com.example.composepractice

import android.widget.FrameLayout
import androidx.compose.runtime.AbstractApplier

internal class FrameLayoutAppiler(
    val frameLayout: FrameLayout
) : AbstractApplier<FrameLayoutNode>(FrameLayoutRootNode) {

    private val decorations = mutableListOf<FrameLayoutNode>()

    override fun onClear() {
        decorations.forEach {
            it.onCleared()
        }
        decorations.clear()
    }

    override fun insertBottomUp(index: Int, instance: FrameLayoutNode) {
        decorations.add(index, instance)
        instance.onAttached()
    }

    override fun insertTopDown(index: Int, instance: FrameLayoutNode) {
        //탑다운 방식은 사용하지 않음
    }

    override fun move(from: Int, to: Int, count: Int) {
        decorations.move(from, to, count)
    }

    override fun remove(index: Int, count: Int) {
        repeat(count) {
            decorations[index + it].onRemoved()
        }
        decorations.remove(index, count)
    }
}

private object FrameLayoutRootNode : FrameLayoutNode

internal interface FrameLayoutNode {
    fun onAttached() {}
    fun onRemoved() {}
    fun onCleared() {}
}

