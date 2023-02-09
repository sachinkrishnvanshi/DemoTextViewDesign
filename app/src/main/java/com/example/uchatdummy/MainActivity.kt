package com.example.uchatdummy

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.uchatdummy.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.etPhone.onFocusChangeListener =
            OnFocusChangeListener { _: View?, hasFocus: Boolean ->
                if (hasFocus) {
                    mBinding.tvHelperAppearance.visibility = View.VISIBLE
                    enableContinue()

                } else {
                    mBinding.tvHelperAppearance.visibility = View.GONE
                }
            }


    }

    private fun enableContinue() {
        mBinding.btnContinueDisable.isEnabled=false
        mBinding.etPhone.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                mBinding.btnContinueDisable.isEnabled= s.toString().trim { it <= ' ' }.isNotEmpty()
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
                mBinding.btnContinueDisable.visibility=View.VISIBLE
                mBinding.btnContinueEnable.visibility=View.GONE
            }

            override fun afterTextChanged(s: Editable) {
                if(s.toString().trim().length==8){
                    mBinding.tvHelperAppearance.visibility=View.VISIBLE
                    mBinding.btnContinueDisable.isEnabled=false
                    mBinding.btnContinueDisable.visibility = View.GONE
                    mBinding.btnContinueEnable.isEnabled = true
                    mBinding.btnContinueEnable.visibility = View.VISIBLE
                } else {
                    mBinding.tvErrorAppearance.visibility=View.VISIBLE
                }

            }
        })
    }
}