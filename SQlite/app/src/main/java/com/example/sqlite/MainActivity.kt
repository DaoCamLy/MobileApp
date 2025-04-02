package com.example.sqlite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.sqlite.helper.DatabaseHelper

class MainActivity : ComponentActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var edtName: EditText
    private lateinit var edtPhone: EditText
    private lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)

        edtName = findViewById(R.id.edtName)
        edtPhone = findViewById(R.id.edtPhone)
        txtResult = findViewById(R.id.txtResult)


        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val name = edtName.text.toString()
            val phone = edtPhone.text.toString()
            val inserted = dbHelper.insertData(name, phone)
            txtResult.text = if (inserted > 0) "Data inserted successfully" else "Failed to insert data"
        }

        findViewById<Button>(R.id.btnUpdate).setOnClickListener {
            val name = edtName.text.toString()
            val phone = edtPhone.text.toString()
            val updated = dbHelper.updateData(name, phone)
            txtResult.text = if (updated > 0) "Data updated successfully" else "Failed to update data"
        }


        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            val name = edtName.text.toString()
            val deleted = dbHelper.deleteData(name)
            txtResult.text = if (deleted > 0) "Data deleted successfully" else "Failed to delete data"
        }


        findViewById<Button>(R.id.btnShow).setOnClickListener {
            txtResult.text = dbHelper.getAllData()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
