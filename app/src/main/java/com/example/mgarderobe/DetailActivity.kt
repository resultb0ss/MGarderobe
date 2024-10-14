package com.example.mgarderobe

import android.annotation.SuppressLint
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
    private var article: Article? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("article")) {
            article = intent.getSerializableExtra("article") as Article
        }


        article?.image?.let { binding.detailActivityImageViewIV.setImageResource(it) }
        binding.detailActivityNameTextViewTV.text = article?.name
        binding.detailActivitysDescriptionTextViewTV.text = article?.description


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
                }
                dialog.setNegativeButton("Отмена") {_,_ ->}
                dialog.create().show()
                false
        }

    }
}