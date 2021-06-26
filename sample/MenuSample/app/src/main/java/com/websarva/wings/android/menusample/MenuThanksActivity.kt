package com.websarva.wings.android.menusample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView

class MenuThanksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_thanks)

        // get the Data passed from List Page
        val menuName = intent.getStringExtra("menuName")
        val menuPrice = intent.getStringExtra("menuPrice")

        // get TextView to display set meal and price.
        val tvMenuName = findViewById<TextView>(R.id.tvMenuName)
        val tvMenuPrice = findViewById<TextView>(R.id.tvMenuPrice)

        // display set meal and price in TextView.
        tvMenuName.text = menuName
        tvMenuPrice.text = menuPrice

        // because supportActionBar is Nullable, useing sefe call operator (?.)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // prepare returnValue with initial value "true".
        var returnVal = true
        // if Return of Menu is selected, Activity is finish.
        if (item.itemId == android.R.id.home) {
            finish()
        }
        else {
            // Call method of the same name in super class, let its return value be returnVal.
            returnVal = super.onOptionsItemSelected(item)
        }

        return returnVal
    }


}