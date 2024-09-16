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

private const val NOT_ANSWERED = 0
private const val CORRECT_ANSWER = 1
private const val INCORRECT_ANSWER = 2
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
    private val questionAnswers = MutableList(questionBank.size) { NOT_ANSWERED }


    private fun buttonChecker(checkerValue: Boolean){
        if (questionAnswers[currentIndex]!= NOT_ANSWERED){
            //Question already answered
            return
        }

        // if both boolean values are same, also update the questionAnswers[currentIndex] to keep track of it
           val messageId = if(questionBank[currentIndex].answer == checkerValue){
               questionAnswers[currentIndex] = CORRECT_ANSWER
                R.string.correctToast
            }
        else{
            questionAnswers[currentIndex] = INCORRECT_ANSWER
            R.string.incorrectToast
            }

        // Disable the buttons after an answer is given
        binding.TrueButton.isEnabled = false
        binding.FalseButton.isEnabled = false

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

            // Re-enable buttons for the new question if it has not been answered
            if (questionAnswers[currentIndex] == NOT_ANSWERED) {
                binding.TrueButton.isEnabled = true
                binding.FalseButton.isEnabled = true
            }

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

            // Re-enable buttons for the new question if it has not been answered
            if (questionAnswers[currentIndex] == NOT_ANSWERED) {
                binding.TrueButton.isEnabled = true
                binding.FalseButton.isEnabled = true
            }

        }





    }
}