package its.nugrohodimas.capstonebangkit.ui.home.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import its.nugrohodimas.capstonebangkit.databinding.ActivityDetailArticleBinding
import its.nugrohodimas.core.domain.model.Article

class DetailArticleActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataIntent = intent.getParcelableExtra<Article>(EXTRA_DATA)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding) {
            Glide.with(applicationContext)
                .load(dataIntent?.image)
                .into(imgDetailArticle)
            tvDetailArticle.text = dataIntent?.content
            tvDetailAuthor.text = dataIntent?.author
            tvDetailDate.text = dataIntent?.date
            tvDetailArticleTitle.text = dataIntent?.title
        }
    }
}