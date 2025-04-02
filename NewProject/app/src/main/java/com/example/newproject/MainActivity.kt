package com.example.newproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.newproject.helper.PreferenceHelper
import com.example.newproject.ui.theme.NewProjectTheme

class MainActivity : ComponentActivity() {

    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var txtResult: TextView
    private lateinit var prefHelper: PreferenceHelper
    private lateinit var btnDelete: Button
    private lateinit var btnSave: Button
    private lateinit var btnShow: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
        txtResult = findViewById(R.id.txtResult)

        btnSave = findViewById(R.id.btnSave)
        btnDelete = findViewById(R.id.btnDelete)
        btnShow = findViewById(R.id.btnShow)

        prefHelper = PreferenceHelper(this)

        btnSave.setOnClickListener {
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()
            prefHelper.saveUser(username, password)
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
        }

        btnDelete.setOnClickListener {
            prefHelper.deleteUser()
            Toast.makeText(this, "Data deleted", Toast.LENGTH_SHORT).show()
        }

        btnShow.setOnClickListener {
            val user = prefHelper.getUser()
            if (user.first != null && user.second != null) {
                txtResult.text = "Username: ${user.first}, Password: ${user.second}"
            } else {
                txtResult.text = "No data found"
            }
        }
    }
}
