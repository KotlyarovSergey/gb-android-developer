package com.ksv.gb.lesson11

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import com.ksv.gb.lesson11.databinding.ActivityMainBinding
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

private const val PREFERENCE_NAME = "prefs_name"
private const val KEY_STRING_NAME = "key_string"
private const val FILE_NAME = "fileName.txt"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences
    private val person = Person("Segrey", 40)
    private val repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)

        val editor = prefs.edit()
        editor.putString(KEY_STRING_NAME, "My string value")
        editor.apply()

//        editor.remove(KEY_STRING_NAME)
//        editor.clear()
//
//        val doesContain: Boolean = prefs.contains(KEY_STRING_NAME)

        binding.sharedPrefsButton.setOnClickListener {
            val str = prefs.getString(KEY_STRING_NAME, "none")
            binding.tv1.text = str
        }

        val fragmentArgs = Bundle().apply {
            putParcelable("Person", person)
        }

        val fragment = MyFragment().apply {
            arguments = fragmentArgs
        }


        binding.saveButton.setOnClickListener {
            val inputText = binding.editText.text.toString()
            when (binding.switchAdd.isChecked) {
                true -> saveTextAdd(inputText)
                false -> saveText(inputText)
            }

        }
        binding.loadButton.setOnClickListener {
            val readedText = readText()
            binding.tv2.text = readedText
        }
        binding.switchAdd.setOnClickListener {
            val switch = it as SwitchCompat
            switch.text = if (switch.isChecked)
                "Дописать файл"
            else
                "Перезаписать файл"
        }

        //  deleteFile(FILE_NAME)


        repository.saveToPreferences(this, "Данные из преференций")
        binding.loadDataButton.setOnClickListener {
            val str = repository.loadText(this)
            binding.tv3.text = str
        }

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putParcelable("Person", person)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getParcelable<Person>("Person")
    }

    private fun saveText(textForSave: String) {
        var fos: FileOutputStream? = null
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE)
            fos.write(textForSave.toByteArray())

            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show()
        } catch (ex: IOException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
        } finally {
            fos?.close()
        }
    }

    private fun saveTextAdd(textForSave: String) {
        var fos: FileOutputStream? = null
        try {
            fos = openFileOutput(FILE_NAME, MODE_APPEND)
            fos.write(textForSave.toByteArray())

            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show()
        } catch (ex: IOException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
        } finally {
            fos?.close()
        }
    }

    private fun readText(): String {
        var fin: FileInputStream? = null
        return try {
            fin = openFileInput(FILE_NAME)
            val bytes = ByteArray(fin.available())
            fin.read(bytes)
            String(bytes)
        } catch (ex: IOException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
            ""
        } finally {
            fin?.close()
        }
    }
}