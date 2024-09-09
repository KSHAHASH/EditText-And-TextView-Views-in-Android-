package com.bignerdranch.android.edittexts

import androidx.annotation.StringRes

data class Question(@StringRes val textResId:Int, val answer:Boolean ) {
}