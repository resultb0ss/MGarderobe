package com.example.mgarderobe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mgarderobe.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var article: Article
    private var articles = ArticlesDataBase.articles

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("article")) {
            article = intent.getSerializableExtra("article") as Article
        }


        binding.detailActivityImageViewIV.setImageResource(article.image)
        binding.detailActivityNameTextViewTV.text = article.name
        binding.detailActivitysDescriptionTextViewTV.text = article.description


        binding.detailActivityConstraintLayout.setOnLongClickListener  {
                val dialog = AlertDialog.Builder(this)
                val inflater = this.layoutInflater
                val dialogView = inflater.inflate(R.layout.update_dialog, null)
                dialog.setView(dialogView)
                val editName: EditText = dialogView.findViewById(R.id.updateDialogNameET)
                val editDescription: EditText = dialogView.findViewById(R.id.updateDialogDescriptionET)

                dialog.setTitle("Обновить запись")
                dialog.setMessage("Введите данные ниже")
                dialog.setPositiveButton("Обновить") {_,_ ->
                    binding.detailActivityNameTextViewTV.text = editName.text.toString()
                    binding.detailActivitysDescriptionTextViewTV.text = editDescription.text.toString()
                    val newName = binding.detailActivityNameTextViewTV.text.toString()
                    val newDescription = binding.detailActivitysDescriptionTextViewTV.text.toString()
                    val newArticle = Article(article.id,newName,newDescription,article.image)

                    val index = search(articles,article)
                    swap(articles,index,newArticle)

                    val intent = Intent(this, SecondActivity::class.java)
                    intent.putExtra("index", index)
                    startActivity(intent)

                }
                dialog.setNegativeButton("Отмена") {_,_ ->}
                dialog.create().show()
                false
        }

    }

    private fun swap(articles: MutableList<Article>, index: Int, newArticle: Article){
        articles.add(index + 1, newArticle)
        articles.removeAt(index)
    }

    private fun search(articles: MutableList<Article>, oldArticle: Article): Int {
        var result = -1
        for (i in articles.indices) {
            if ( oldArticle.id == articles[i].id ) result = i
        }
        return result
    }


}