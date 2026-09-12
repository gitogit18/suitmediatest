package com.example.suitmediatest.ui.first

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediatest.databinding.ActivityFirstBinding
import com.example.suitmediatest.ui.second.SecondActivity

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCheck.setOnClickListener {
            val nameText = binding.etName.text.toString()
            val palindromeText = binding.etPalindrome.text.toString()

            if (nameText.isBlank() || palindromeText.isBlank()) {
                AlertDialog.Builder(this)
                    .setMessage("Please Insert Name / Palindrome Check")
                    .setPositiveButton("OK", null)
                    .show()
                return@setOnClickListener
            }

            val result = isPalindrome(palindromeText)

            val message = if (result) {
                 "isPalindrome"
            } else {
                 "Not Palindrome"
            }

            AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        }

        binding.btnNext.setOnClickListener {
            val name = binding.etName.text.toString()

            if (name.isBlank()) {
                AlertDialog.Builder(this)
                    .setMessage("Please Insert Name first")
                    .setPositiveButton("OK", null)
                    .show()
                return@setOnClickListener
            }

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("USER_NAME", name)
            startActivity(intent)
        }
    }

    private fun isPalindrome(text: String): Boolean {
        val normalized = text
            .lowercase()
            .filter { !it.isWhitespace() }

        return normalized == normalized.reversed()
    }
}