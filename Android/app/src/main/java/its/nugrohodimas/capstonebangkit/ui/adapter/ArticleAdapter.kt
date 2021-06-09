package its.nugrohodimas.capstonebangkit.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import its.nugrohodimas.capstonebangkit.R
import its.nugrohodimas.capstonebangkit.ui.home.article.DetailArticleActivity
import its.nugrohodimas.core.domain.model.Article

class ArticleAdapter(private val articleList: ArrayList<Article>) :
    RecyclerView.Adapter<ArticleAdapter.UserAdapterVH>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAdapterVH {
        return UserAdapterVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_information, parent, false)
        )
    }

    class UserAdapterVH(itenView: View) : RecyclerView.ViewHolder(itenView) {
        var author: TextView = itemView.findViewById(R.id.tv_item_author)
        var title: TextView = itemView.findViewById(R.id.tv_item_title)
        var image: ImageView = itemView.findViewById(R.id.img_item_information)
    }

    override fun onBindViewHolder(holder: UserAdapterVH, position: Int) {
        val article: Article = articleList[position]
        holder.author.text = article.author
        holder.title.text = article.title
        Glide.with(holder.itemView)
            .load(article.image)
            .into(holder.image)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailArticleActivity::class.java)
            intent.putExtra(DetailArticleActivity.EXTRA_DATA, article)
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int = articleList.size
}
