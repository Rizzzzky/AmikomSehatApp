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

class AddObatActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var menuId1: EditText
    private lateinit var menuName1: EditText
    private lateinit var menuPrice1: EditText
    private lateinit var imageMenu1: ImageView

    private var selectedImage: Bitmap? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val selectedImageUri = data?.data
                selectedImage = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                imageMenu1.setImageBitmap(selectedImage)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_obat)

        databaseHelper = DatabaseHelper(this)

        menuId1 = findViewById(R.id.menuId1)
        menuName1 = findViewById(R.id.menuName1)
        menuPrice1 = findViewById(R.id.menuPrice1)
        imageMenu1 = findViewById(R.id.imageMenu1)

        val buttonAddImage: Button = findViewById(R.id.buttonAddImage)
        buttonAddImage.setOnClickListener {
            openGalleryForImage()
        }

        val buttonSaveMenu: Button = findViewById(R.id.buttonSaveMenu)
        buttonSaveMenu.setOnClickListener {
            saveObat()
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        getContent.launch(intent)
    }

    private fun saveObat() {
        val id = menuId1.text.toString()
        val name = menuName1.text.toString()
        val harga = menuPrice1.text.toString()

        if (id.isNotEmpty() && name.isNotEmpty() && harga.isNotEmpty() && selectedImage != null) {

            databaseHelper.addObat(id,name, harga, selectedImage!!)


            menuId1.text.clear()
            menuName1.text.clear()
            menuPrice1.text.clear()
            imageMenu1.setImageResource(R.drawable.ic_photo_camera)

            Toast.makeText(this, "Obat added successfully", Toast.LENGTH_SHORT).show()
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
