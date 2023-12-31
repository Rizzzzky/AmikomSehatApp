package com.example.amikomsehat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class PembayaranActivity : AppCompatActivity() {
    private lateinit var tvJumlahObat: TextView
    private lateinit var tvTotalPrice: TextView
    private lateinit var tvPaymentStatus: TextView
    private lateinit var UserMoney: EditText
    private lateinit var btnProcessPayment: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        // Initialize views
        tvJumlahObat = findViewById(R.id.tvJumlahObat)
        tvTotalPrice = findViewById(R.id.tvTotalPrice)
        tvPaymentStatus = findViewById(R.id.tvPaymentStatus)
        UserMoney = findViewById(R.id.UserMoney)
        btnProcessPayment = findViewById(R.id.btnProcessPayment)

        // Retrieve order data from the intent
        val intent = intent
        val JumlahObat = intent.getIntExtra("totalItems", 0)
        val totalPrice = intent.getIntExtra("totalPrice", 0)

        // Display total price
        tvJumlahObat.text = "Jumlah Obat : $JumlahObat"
        tvTotalPrice.text = "Total Harga: Rp $totalPrice"

        // Set onClickListener for the payment button
        btnProcessPayment.setOnClickListener {
            processPayment(totalPrice)
        }
    }

    private fun processPayment(totalPrice: Int) {

        val userInput = UserMoney.text.toString().toIntOrNull()


        if (userInput != null) {

            val change = userInput - totalPrice


            if (change >= 0) {
                tvPaymentStatus.text = "Pembayaran Berhasil! Kembalian: Rp $change"
            } else {
                tvPaymentStatus.text = "Pembayaran Gagal. Uang tidak mencukupi."
            }
        } else {
            tvPaymentStatus.text = "Masukkan jumlah uang yang valid."
        }
    }
}