package com.jjmoo.android.jetpackdemo.base

import dagger.Component

/**
 * @author Zohn
 */
@Component
interface AppComponent {
    fun inject(appJoint: AppJoint)
}