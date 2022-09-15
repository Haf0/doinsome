package com.qtn.doinsome.customview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import androidx.appcompat.widget.AppCompatEditText
import com.qtn.doinsome.R


class EditTextEmail: AppCompatEditText {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                error = if (p0.toString().isNotEmpty()) {
                    if (emailValidation(p0.toString())) null else context.getString(
                        R.string.email_is_invalid
                    )
                }else{
                    context.getString(R.string.email_isEmpy)
                }
            }
            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun emailValidation(s: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(s).matches()
    }
}
