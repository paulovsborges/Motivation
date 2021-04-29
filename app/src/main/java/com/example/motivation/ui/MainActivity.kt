package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.Infra.MotivationConstants
import com.example.motivation.Infra.SecurityPreferences
import com.example.motivation.R
import com.example.motivation.repository.mock
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    private var mPhraseFilter: Int = MotivationConstants.PHRASEFILTER.infinity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        infinity.setOnClickListener(this)
        sun.setOnClickListener(this)
        happy.setOnClickListener(this)
        button_new_phrase.setOnClickListener(this)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        infinity.setColorFilter(resources.getColor(R.color.pink))
        handleNewPhrase()

        mSecurityPreferences = SecurityPreferences(this)

        val name = mSecurityPreferences.getString(MotivationConstants.KEY.person_Name)
        text_name.text = "Olá $name!"
    }

    override fun onClick(view: View) {

        val id = view.id

        if (id == R.id.button_new_phrase) {
            handleNewPhrase()
        } else if (id == R.id.infinity || id == R.id.happy || id == R.id.sun) {

            handleFilter(id)
        }


    }

    private fun handleNewPhrase() {

        text_phrase.text = mock().getPhrase(mPhraseFilter)

    }


    private fun handleFilter(id: Int) {


        infinity.setColorFilter(resources.getColor(R.color.white))
        happy.setColorFilter(resources.getColor(R.color.white))
        sun.setColorFilter(resources.getColor(R.color.white))




        when (id) {
            R.id.infinity -> {
                infinity.setColorFilter(resources.getColor(R.color.pink))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.infinity
            }
            R.id.happy -> {
                happy.setColorFilter(resources.getColor(R.color.pink))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.happy
            }
            R.id.sun -> {
                sun.setColorFilter(resources.getColor(R.color.pink))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.sun
            }
        }
    }
}