package com.example.groffy

import android.app.ActionBar
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var rvGrocery: RecyclerView
    private val list = ArrayList<Grocery>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = resources.getString(R.string.app_name_full)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.green)))

        rvGrocery = findViewById(R.id.rv_groceries)
        rvGrocery.setHasFixedSize(true)

        list.addAll(getListGroceries())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val aboutApp = Intent(this@MainActivity, AboutApp::class.java)
                startActivity(aboutApp)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSelectedGrocery(grocery: Grocery) {
        val detailGrocery = Intent(this@MainActivity, DetailGrocery::class.java)
        detailGrocery.putExtra(DetailGrocery.EXTRA_GROCERY, grocery)
        startActivity(detailGrocery)
    }

    private fun getListGroceries(): ArrayList<Grocery> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataPrice = resources.getIntArray(R.array.data_price)
        val dataNutrients = resources.getStringArray(R.array.data_nutrients)
        val dataProcessingBenefits = resources.getStringArray(R.array.data_processing_benefits)
        val listGroceries = ArrayList<Grocery>()
        for (i in dataName.indices) {
            val grocery = Grocery(dataName[i], dataDescription[i], dataPhoto[i], dataPrice[i], dataNutrients[i], dataProcessingBenefits[i])
            listGroceries.add(grocery)
        }
        return listGroceries
    }

    private fun showRecyclerList() {
        rvGrocery.layoutManager = LinearLayoutManager(this)
        val listGroceryAdapter = ListGroceryAdapter(list)
        rvGrocery.adapter = listGroceryAdapter

        listGroceryAdapter.setOnItemClickCallback(
            object : ListGroceryAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Grocery) {
                    showSelectedGrocery(data)
                }
            }
        )
    }
}