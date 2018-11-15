package com.petersommerhoff.kudoofinal.view.common

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import kotlin.reflect.KClass

/**
 * @author Peter Sommerhoff
 */
fun <T : ViewModel> FragmentActivity.getViewModel(modelClass: KClass<T>): T =
    ViewModelProviders.of(this).get(modelClass.java)

