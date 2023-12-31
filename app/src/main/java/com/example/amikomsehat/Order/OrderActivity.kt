package com.example.amikomsehat



import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton

class OrderActivity : AppCompatActivity() {
    var ordertitle:String? = null
    var paket1 = 15000
    var paket2 = 14000
    var paket3 = 7000
    var paket4 = 13000
    var paket5 = 16500
    var paket6 = 16000
    var countP1 = 0
    var countP2 = 0
    var countP3 = 0
    var countP4 = 0
    var countP5 = 0
    var countP6 = 0
    var totalItems = 0
    var totalPrice = 0
    var imageAdd1: ImageView? = null
    var imageAdd2: ImageView? = null
    var imageAdd3: ImageView? = null
    var imageAdd4: ImageView? = null
    var imageAdd5: ImageView? = null
    var imageAdd6: ImageView? = null
    var imageMinus1: ImageView? = null
    var imageMinus2: ImageView? = null
    var imageMinus3: ImageView? = null
    var imageMinus4: ImageView? = null
    var imageMinus5: ImageView? = null
    var imageMinus6: ImageView? = null
    var toolbar: Toolbar? = null
    var tvPaket1: TextView? = null
    var tvPaket2: TextView? = null
    var tvPaket3: TextView? = null
    var tvPaket4: TextView? = null
    var tvPaket5: TextView? = null
    var tvPaket6: TextView? = null

    var tvJumlahObat: TextView? = null
    var tvTotalPrice: TextView? = null
    var btnCheckout: MaterialButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)


        // Initialize views
        initViews()

        // Set click listeners
        setClickListeners()
    }

    private fun initViews() {
        // Initialize your views here
        imageAdd1 = findViewById(R.id.imageAdd1)
        imageAdd2 = findViewById(R.id.imageAdd2)
        imageAdd3 = findViewById(R.id.imageAdd3)
        imageAdd4 = findViewById(R.id.imageAdd4)
        imageAdd5 = findViewById(R.id.imageAdd5)
        imageAdd6 = findViewById(R.id.imageAdd6)

        imageMinus1 = findViewById(R.id.imageMinus1)
        imageMinus2 = findViewById(R.id.imageMinus2)
        imageMinus3 = findViewById(R.id.imageMinus3)
        imageMinus4 = findViewById(R.id.imageMinus4)
        imageMinus5 = findViewById(R.id.imageMinus5)
        imageMinus6 = findViewById(R.id.imageMinus6)

        toolbar = findViewById(R.id.toolbar)
        tvPaket1 = findViewById(R.id.tvPaket1)
        tvPaket2 = findViewById(R.id.tvPaket2)
        tvPaket3 = findViewById(R.id.tvPaket3)
        tvPaket4 = findViewById(R.id.tvPaket4)
        tvPaket5 = findViewById(R.id.tvPaket5)
        tvPaket6 = findViewById(R.id.tvPaket6)

        tvJumlahObat = findViewById(R.id.tvJumlahObat)
        tvTotalPrice = findViewById(R.id.tvTotalPrice)
        btnCheckout = findViewById(R.id.btnCheckout)
    }

    private fun setClickListeners() {
        // Set click listeners for quantity controls
        imageAdd1?.setOnClickListener { incrementQuantity(tvPaket1, 1) }
        imageAdd2?.setOnClickListener { incrementQuantity(tvPaket2, 2) }
        imageAdd3?.setOnClickListener { incrementQuantity(tvPaket3, 3) }
        imageAdd4?.setOnClickListener { incrementQuantity(tvPaket4, 4) }
        imageAdd5?.setOnClickListener { incrementQuantity(tvPaket5, 5) }
        imageAdd6?.setOnClickListener { incrementQuantity(tvPaket6, 6) }

        imageMinus1?.setOnClickListener { decrementQuantity(tvPaket1, 1) }
        imageMinus2?.setOnClickListener { decrementQuantity(tvPaket2, 2) }
        imageMinus3?.setOnClickListener { decrementQuantity(tvPaket3, 3) }
        imageMinus4?.setOnClickListener { decrementQuantity(tvPaket4, 4) }
        imageMinus5?.setOnClickListener { decrementQuantity(tvPaket5, 5) }
        imageMinus6?.setOnClickListener { decrementQuantity(tvPaket6, 6) }

        // Set click listener for checkout button
        btnCheckout?.setOnClickListener { v: View? ->
            // Your existing code...

            // Create an Intent to start PaymentActivity
            val paymentIntent = Intent(this@OrderActivity, PembayaranActivity::class.java)
            paymentIntent.putExtra("totalItems", totalItems)
            paymentIntent.putExtra("totalPrice", totalPrice)

            // Start the PaymentActivity
            startActivity(paymentIntent)
            finish() // Optionally finish the OrderActivity
        }
        }



    // ... (Previous code)

    private fun incrementQuantity(tvQuantity: TextView?, paket: Int) {
        when (paket) {
            1 -> {
                countP1++
                tvQuantity?.text = countP1.toString()
            }
            2 -> {
                countP2++
                tvQuantity?.text = countP2.toString()
            }
            3 -> {
                countP3++
                tvQuantity?.text = countP3.toString()
            }
            4 -> {
                countP4++
                tvQuantity?.text = countP4.toString()
            }
            5 -> {
                countP5++
                tvQuantity?.text = countP5.toString()
            }
            6 -> {
                countP6++
                tvQuantity?.text = countP6.toString()
            }
        }

        updateTotal()
    }

    private fun decrementQuantity(tvQuantity: TextView?, paket: Int) {
        when (paket) {
            1 -> {
                if (countP1 > 0) {
                    countP1--
                    tvQuantity?.text = countP1.toString()
                }
            }
            2 -> {
                if (countP2 > 0) {
                    countP2--
                    tvQuantity?.text = countP2.toString()
                }
            }
            3 -> {
                if (countP3 > 0) {
                    countP3--
                    tvQuantity?.text = countP3.toString()
                }
            }
            4 -> {
                if (countP4 > 0) {
                    countP4--
                    tvQuantity?.text = countP4.toString()
                }
            }
            5 -> {
                if (countP5 > 0) {
                    countP5--
                    tvQuantity?.text = countP5.toString()
                }
            }
            6 -> {
                if (countP6 > 0) {
                    countP6--
                    tvQuantity?.text = countP6.toString()
                }
            }
        }

        updateTotal()
    }

    private fun updateTotal() {
        totalItems = countP1 + countP2 + countP3 + countP4 + countP5 + countP6
        totalPrice = (countP1 * paket1) + (countP2 * paket2) + (countP3 * paket3) +
                (countP4 * paket4) + (countP5 * paket5) + (countP6 * paket6)

        tvJumlahObat?.text = "$totalItems items"
        tvTotalPrice?.text = "Rp $totalPrice"
    }

    // Additional methods...

}
