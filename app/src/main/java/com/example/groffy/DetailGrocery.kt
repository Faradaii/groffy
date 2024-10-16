package com.example.groffy

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailGrocery : AppCompatActivity() {

    companion object {
        const val EXTRA_GROCERY = "extra_grocery"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_grocery)

        supportActionBar?.title = "Detail Grocery"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.green)))

        val tvTitle: TextView = findViewById(R.id.tv_grocery_title)
        val tvDescription: TextView = findViewById(R.id.tv_grocery_description)
        val tvNutrients: TextView = findViewById(R.id.tv_grocery_nutrients)
        val tvPrice: TextView = findViewById(R.id.tv_grocery_price)
        val tvProcessingBenefits: TextView = findViewById(R.id.tv_grocery_processing_benefits)
        val imageViewPhoto: ImageView = findViewById(R.id.grocery_image)
        val buttonShare: android.widget.Button = findViewById<android.widget.Button>(R.id.action_share)

        val grocery = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Grocery>(EXTRA_GROCERY, Grocery::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Grocery>(EXTRA_GROCERY)
        }
        if (grocery != null) {
            val textTitle = grocery.name.toString()
            val textDescription = grocery.description.toString()
            val textNutrients = grocery.nutrients.toString()
            val textPrice = grocery.price.toString()
            val textProcessingBenefit = grocery.processingBenefits.toString()
            val photo = grocery.photo
            Glide.with(this)
                .load(photo)
                .into(imageViewPhoto)
            tvTitle.text = textTitle
            tvDescription.text = textDescription
            tvNutrients.text = textNutrients
            tvPrice.text = "Rp. $textPrice"
            tvProcessingBenefits.text = textProcessingBenefit

            buttonShare.setOnClickListener() {
                val shareText = "$textTitle\n$textDescription\n$textNutrients\n$textPrice"
                val shareIntent = android.content.Intent()
                shareIntent.action = android.content.Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareText)
                startActivity(shareIntent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}