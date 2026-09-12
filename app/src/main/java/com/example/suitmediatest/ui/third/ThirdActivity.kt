package com.example.suitmediatest.ui.third

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
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

        userAdapter = UserAdapter { _ ->

        }

        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@ThirdActivity)
            adapter = userAdapter
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.collect { users ->
                    userAdapter.submitList(users)
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

    }
}