package life.league.challenge.kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import life.league.challenge.kotlin.databinding.PostItemBinding
import life.league.challenge.kotlin.model.PostDetail

class PostListAdapter: ListAdapter<PostDetail, PostListAdapter.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(private val binding: PostItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: PostDetail) {
            Glide.with(binding.root.context)
                .load(item.photoUrl)
                .into(binding.photo)
            binding.name.text = item.userName
            binding.title.text = item.title
            binding.description.text = item.description
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<PostDetail>() {
        override fun areItemsTheSame(oldItem: PostDetail, newItem: PostDetail): Boolean =
            oldItem.Id == newItem.Id

        override fun areContentsTheSame(oldItem: PostDetail, newItem: PostDetail): Boolean =
            oldItem.userId == newItem.userId && oldItem.userName == newItem.userName &&
                    oldItem.title == newItem.title && oldItem.description == newItem.description &&
                    oldItem.photoUrl == newItem.photoUrl
    }
}