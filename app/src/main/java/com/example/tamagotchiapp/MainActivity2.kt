package com.example.tamagotchiapp
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



class MainActivity2 : AppCompatActivity() {
    private var petHungerLevel = 50
    private var petCleanLevel = 50
    private var petHappyLevel = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val buttonFeed = findViewById<Button>(R.id.buttonFeed)
        val buttonClean = findViewById<Button>(R.id.buttonClean)
        val buttonPlay = findViewById<Button>(R.id.buttonPlay)
        val petImage = findViewById<ImageView>(R.id.petImage)

        // Set initial values
        updateUI()

        buttonFeed.setOnClickListener {
            petHungerLevel += 11
            petCleanLevel += 6
            petHappyLevel += 11

            updateUI()
            petImage.setImageResource(R.drawable.feed_icon)
        }

        buttonClean.setOnClickListener {
            petCleanLevel += 11
            petHappyLevel += 11

            updateUI()
            petImage.setImageResource(R.drawable.tamagotchi_ui)
        }

        buttonPlay.setOnClickListener {
            petHungerLevel += 6
            petCleanLevel += 5
            petHappyLevel += 11

            updateUI()
            petImage.setImageResource(R.drawable.play_icon)
        }

        // Periodically update pet's needs
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(30000) // Update every 30 seconds
                updatePetNeeds()
            }
        }
    }

    private fun updateUI() {
        findViewById<EditText>(R.id.textHunger).setText(petHungerLevel.toString())
        findViewById<EditText>(R.id.textClean).setText(petCleanLevel.toString())
        findViewById<EditText>(R.id.textHappy).setText(petHappyLevel.toString())
    }

    private fun updatePetNeeds() {
        petHungerLevel -= 2
        petCleanLevel -= 1
        petHappyLevel -= 1

        updateUI()
    }
}
