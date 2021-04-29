package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.Infra.MotivationConstants
import com.example.motivation.Infra.SecurityPreferences
import com.example.motivation.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)


        if(supportActionBar != null) {
            supportActionBar!!.hide()
        }
        button_save.setOnClickListener(this)

        verifyName()
    }


    private fun verifyName(){
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.person_Name)
        if(name != ""){
            startActivity(Intent(this, MainActivity::class.java))

            //if you wanna to finish de application before back to the primary screen, use :
            // finish()
        }
    }

    override fun onClick(view: View) {

        val id = view.id
        if( id == R.id.button_save) {

            handleSave()
        }
    }

    private fun handleSave() {

        val name = edit_name.text.toString()
        if(name != "") {

            mSecurityPreferences.storesTRING(MotivationConstants.KEY.person_Name, name)
            startActivity(Intent(this, MainActivity::class.java))
            //and here as well
            // finish()
        } else {
            Toast.makeText(this, "informe um nome válido", Toast.LENGTH_LONG).show()
        }
    }
}