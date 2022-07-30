package com.tiendavirtual.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class MSPtextViewBold(context: Context, attrs: AttributeSet) : AppCompatTextView (context, attrs){
    init {
        applyFront ()
    }
    private fun applyFront(){
        val typeface: Typeface =
            Typeface.createFromAsset(context.assets,"Game Changer Regular.ttf")
        setTypeface(typeface)
    }
}