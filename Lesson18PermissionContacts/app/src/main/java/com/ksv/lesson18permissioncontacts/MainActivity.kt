package com.ksv.lesson18permissioncontacts

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.ksv.lesson18permissioncontacts.databinding.ActivityMainBinding
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
        private val launcher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            getContacts()
            Toast.makeText(this, "permission is $isGranted", Toast.LENGTH_SHORT).show()
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermission()
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getContacts()
            Toast.makeText(this, "permission is Granted", Toast.LENGTH_SHORT).show()
        } else {
            launcher.launch(Manifest.permission.READ_CONTACTS)
        }

    }

    private fun getContacts() {
        val contentUri = ContactsContract.Contacts.CONTENT_URI
        val contactsProjection = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.HAS_PHONE_NUMBER
        )
        val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val phoneProjection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )
        val phoneSelection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?"

        val stringBuilder = StringBuilder()

        contentResolver.query(
            contentUri,
            contactsProjection,
            null,
            null,
            null
        )?.use { curson ->
            val idIndex = curson.getColumnIndex(ContactsContract.Contacts._ID)
            val nameIndex = curson.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            val hasPhoneIndex = curson.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)

            while (curson.moveToNext()){
                stringBuilder.append(curson.getString(nameIndex))
                    .append(": ")
                val hasPhone = curson.getInt(hasPhoneIndex) > 0
                if (hasPhone){
                    val contactId = curson.getString(idIndex)
                    contentResolver.query(
                        phoneUri,
                        phoneProjection,
                        phoneSelection,
                        arrayOf(contactId),
                        null
                    )?.use { phoneCursor ->
                        val numberIndex =
                            phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        while (phoneCursor.moveToNext()){
                            stringBuilder.append(phoneCursor.getString(numberIndex))
                                .append(", ")
                        }

                    }

                } else {
                    stringBuilder.append("no phone")
                }
                stringBuilder.append("\n")

            }
        }

        binding.textView.text = stringBuilder.toString()
    }

}