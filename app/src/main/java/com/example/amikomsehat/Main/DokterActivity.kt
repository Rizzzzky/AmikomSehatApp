package com.example.amikomsehat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DokterActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DokterAdapter //
    private lateinit var btnDokter: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dokter)

        dbHelper = DatabaseHelper(this)

        recyclerView = findViewById(R.id.recyclerDokter)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val dokterList = dbHelper.showMenu()

        adapter = DokterAdapter(dokterList)
        recyclerView.adapter = adapter
        btnDokter = findViewById(R.id.buttonAddDokter)

        // Set a click listener for the button to navigate to AddDokterActivity
        btnDokter.setOnClickListener {
            val intent = Intent(this, AddDokterActivity::class.java)
            startActivity(intent)

        }
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // Not used for swipe-to-delete, so return false
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedDokter = adapter.getItem(position)

                // Delete the doctor from the database
                val deletedSuccessfully = dbHelper.deleteDokter(deletedDokter.id)

                if (deletedSuccessfully) {
                    // Remove the item from the adapter's data set
                    adapter.removeItem(position)

                    // Notify the adapter that an item has been removed
                    adapter.notifyItemRemoved(position)
                } else {
                    // Handle deletion failure
                    // You might want to show an error message to the user
                    Toast.makeText(this@DokterActivity, "Failed to delete doctor", Toast.LENGTH_SHORT).show()
                }
            }
        })

// Attach the ItemTouchHelper to the RecyclerView
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    }