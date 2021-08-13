package life.league.challenge.kotlin.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.adapter.PostListAdapter
import life.league.challenge.kotlin.databinding.ActivityMainBinding
import life.league.challenge.kotlin.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: UserViewModel by viewModels()
    private val adapter = PostListAdapter()

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter

        viewModel.posts.observe(this) {
            adapter.submitList(it)
        }

        //Load data
        viewModel.fetch()
    }
}
