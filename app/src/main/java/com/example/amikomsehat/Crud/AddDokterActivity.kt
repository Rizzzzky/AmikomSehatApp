package com.example.amikomsehat

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

import java.io.ByteArrayOutputStream

class AddDokterActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var menuId: EditText
    private lateinit var menuName: EditText
    private lateinit var menuPrice: EditText
    private lateinit var imageMenu: ImageView

    private var selectedImage: Bitmap? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val selectedImageUri = data?.data
                selectedImage = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                imageMenu.setImageBitmap(selectedImage)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dokter)

        databaseHelper = DatabaseHelper(this)

        menuId = findViewById(R.id.menuId)
        menuName = findViewById(R.id.menuName)
        menuPrice = findViewById(R.id.menuPrice)
        imageMenu = findViewById(R.id.imageMenu)

        val buttonAddImage: Button = findViewById(R.id.buttonAddImage)
        buttonAddImage.setOnClickListener {
            openGalleryForImage()
        }

        val buttonSaveMenu: Button = findViewById(R.id.buttonSaveMenu)
        buttonSaveMenu.setOnClickListener {
            saveDoctor()
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        getContent.launch(intent)
    }

    private fun saveDoctor() {
        val id = menuId.text.toString()
        val name = menuName.text.toString()
        val specialization = menuPrice.text.toString()

        if (id.isNotEmpty() && name.isNotEmpty() && specialization.isNotEmpty() && selectedImage != null) {
            // Add the doctor to the database
            databaseHelper.addDoctor(id,name, specialization, selectedImage!!)

            // Optionally, clear the input fields and reset the image view
            menuId.text.clear()
            menuName.text.clear()
            menuPrice.text.clear()
            imageMenu.setImageResource(R.drawable.ic_photo_camera)

            Toast.makeText(this, "Doctor added successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please fill in all fields and select an image", Toast.LENGTH_SHORT).show()
        }
    }


    private fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}
