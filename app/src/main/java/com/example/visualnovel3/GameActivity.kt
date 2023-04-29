package com.example.visualnovel3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.visualnovel3.databinding.ActivityGameBinding
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.jsonPrimitive
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sceneNumber = intent.getIntExtra("Scene", 1) - 1
        val PlayerName = intent.getStringExtra("Name")

        val JsonFile = applicationContext.resources.openRawResource(R.raw.test)
            .bufferedReader().use { it.readLines() }.joinToString(separator = " ")
        val data = Json.decodeFromString<Scenes>(JsonFile)

        val drawableFonId = applicationContext
            .resources.getIdentifier(data.scene[sceneNumber].fon, "drawable", packageName)
        binding.GameFon.setImageResource(drawableFonId)
        binding.button.text = data.scene[sceneNumber].firstButton

        if (PlayerName != null)
            binding.DialogButton.text = PlayerName + " " + data.scene[sceneNumber].dialog
        else
            binding.DialogButton.text = data.scene[sceneNumber].dialog

        if (data.scene[sceneNumber].secondButton != "")
            binding.button2.text = data.scene[sceneNumber].secondButton
        else binding.button2.visibility = View.INVISIBLE
        if (data.scene[sceneNumber].thirdButton != "")
            binding.button3.text = data.scene[sceneNumber].thirdButton
        else binding.button3.visibility = View.INVISIBLE
        if (!data.scene[sceneNumber].textField) {
            binding.TextInput.visibility = View.INVISIBLE
            binding.TextInputLayout.visibility = View.INVISIBLE
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("Scene", data.scene[sceneNumber].firstButtonNext)
            intent.putExtra("Name", binding.TextInput.text.toString())
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("Scene", data.scene[sceneNumber].secondButtonNext)
            startActivity(intent)
        }

        binding.button3.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("Scene", data.scene[sceneNumber].thirdButtonNext)

            startActivity(intent)
        }
    }
}