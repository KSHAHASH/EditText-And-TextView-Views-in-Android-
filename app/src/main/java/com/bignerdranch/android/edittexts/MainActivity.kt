package com.bignerdranch.android.edittexts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bignerdranch.android.edittexts.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

  private lateinit var binding : ActivityMainBinding

  private var currentIndex = 0

 private val questionBank = listOf(
      Question(R.string.FirstView, true),
      Question(R.string.question_asia, false),
      Question(R.string.question_americas, true),
      Question(R.string.question_africa, false),
      Question(R.string.question_oceans, true),
      Question(R.string.question_mideast, false)
  )

    private fun buttonChecker(checkerValue: Boolean){
           val messageId = if(questionBank[currentIndex].answer == checkerValue){
                R.string.correctToast
            }
        else{
            R.string.incorrectToast
            }
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.TrueButton.setOnClickListener {
            buttonChecker(true)
        }

        binding.FalseButton.setOnClickListener {
            buttonChecker(false)
        }

        binding.topView.setOnClickListener {
            val first = (questionBank[0].textResId)
            val questionFirst = getString(first)

            binding.topView.text = questionFirst

        }

        //Next Button Feature
        binding.NextButton.setOnClickListener {
            currentIndex = (currentIndex +1) % questionBank.size
            val questionResId = questionBank[currentIndex].textResId
            binding.FirstView.text = getString(questionResId)

            }

        //previous button feature

        binding.PreviousButton.setOnClickListener {
            currentIndex = if(currentIndex -1 <0){
                questionBank.size -1
            }
            else{
                (currentIndex -1) % questionBank.size
            }
            val questionResId = questionBank[currentIndex].textResId
            binding.FirstView.text = getString(questionResId)

        }





    }
}