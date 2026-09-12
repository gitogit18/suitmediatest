package com.example.suitmediatest.ui.second

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.ActivitySecondBinding
import com.example.suitmediatest.ui.third.ThirdActivity
import androidx.activity.result.contract.ActivityResultContracts

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    private val chooseUserLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == RESULT_OK) {

                val selectedUserName =
                    result.data?.getStringExtra("SELECTED_USER_NAME")

                binding.tvSelectedUser.text =
                    selectedUserName
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.topAppBar.setPadding(0, systemBars.top, 0, 0)
            binding.btnChooseUser.translationY = -systemBars.bottom.toFloat()
            insets
        }

        val name = intent.getStringExtra("USER_NAME")

        binding.tvName.text = name

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            chooseUserLauncher.launch(intent)
        }
    }
}