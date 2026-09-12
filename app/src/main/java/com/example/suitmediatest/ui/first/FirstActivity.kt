package com.example.suitmediatest.ui.first

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                "$palindromeText is Palindrome"
            } else {
                "$palindromeText Not Palindrome"
            }

            AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        }
    }

    private fun isPalindrome(text: String): Boolean {
        val normalized = text
            .lowercase()
            .filter { !it.isWhitespace() }

        return normalized == normalized.reversed()
    }
}