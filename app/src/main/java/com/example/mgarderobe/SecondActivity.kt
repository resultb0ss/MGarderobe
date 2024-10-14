package com.example.mgarderobe

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mgarderobe.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articles = ArticlesDataBase.articles

        val recyclerViewAdapter = CustomAdapter(articles)
        binding.recyclerViewMainRV.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMainRV.adapter = recyclerViewAdapter
        binding.recyclerViewMainRV.setHasFixedSize(true)
        recyclerViewAdapter.setOnArticleClickListener(object :
        CustomAdapter.OnArticleClickListener {
            override fun onArticleClick(article: Article, position: Int) {
                val intent = Intent(this@SecondActivity,DetailActivity::class.java)
                intent.putExtra("article", article)
                startActivity(intent)
            }

        })


        binding.secondActivityBackButtonBTN.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.secondActivityExitButtonBTN.setOnClickListener {
            this.finishAffinity()
        }

    }
}