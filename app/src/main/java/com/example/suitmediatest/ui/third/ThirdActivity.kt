package com.example.suitmediatest.ui.third

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmediatest.databinding.ActivityThirdBinding
import kotlinx.coroutines.launch

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    private lateinit var userAdapter: UserAdapter

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter { user ->
            val selectedUserName =
                "${user.first_name} ${user.last_name}"

            val resultIntent = Intent().apply {
                putExtra("SELECTED_USER_NAME", selectedUserName)
            }

            setResult(RESULT_OK, resultIntent)
            finish()
        }

        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@ThirdActivity)
            adapter = userAdapter
        }

        binding.rvUsers.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {

                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int,
                    dy: Int
                ) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (dy <= 0) return

                    val layoutManager =
                        recyclerView.layoutManager as LinearLayoutManager

                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem =
                        layoutManager.findLastVisibleItemPosition()

                    if (lastVisibleItem >= totalItemCount - 1) {
                        viewModel.loadNextPage()
                    }
                }
            }
        )

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.users.collect { users ->
                        userAdapter.submitList(users)
                    }
                }

                launch {
                    viewModel.isLoading.collect { isLoading ->
                        binding.progressBar.visibility =
                            if (isLoading) {
                                View.VISIBLE
                            } else {
                                View.GONE
                            }

                        binding.swipeRefresh.isRefreshing =
                            isLoading && userAdapter.currentList.isNotEmpty()

                        binding.tvEmptyState.visibility =
                            if (!isLoading && viewModel.users.value.isEmpty()) {
                                View.VISIBLE
                            } else {
                                View.GONE
                            }
                    }
                }
            }
        }

        viewModel.loadUsers()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.topAppBar.setPadding(0, systemBars.top, 0, 0)
            insets
        }


        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadUsers()
        }

    }
}