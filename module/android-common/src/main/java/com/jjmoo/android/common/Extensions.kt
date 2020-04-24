@file:Suppress("unused")

package com.jjmoo.android.common

import android.view.animation.Animation

/**
 * @author Zohn
 */
inline fun Animation.doFirst(crossinline first: () -> Unit) {
    setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {}

        override fun onAnimationEnd(animation: Animation?) {}

        override fun onAnimationStart(animation: Animation?) {
            first.invoke()
        }
    })
}

inline fun Animation.doAfter(crossinline after: () -> Unit) {
    setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation?) {}

        override fun onAnimationEnd(animation: Animation?) {
            after.invoke()
        }

        override fun onAnimationStart(animation: Animation?) {}
    })
}