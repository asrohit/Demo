package com.yotta.demo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yotta.demo.R
import com.yotta.demo.`class`.RocketRepository
import com.yotta.demo.network.RocketApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = RocketRepository(RocketApi())

        GlobalScope.launch(Dispatchers.Main) {
            val rockets = repository.getRocket()
            Toast.makeText(this@MainActivity,""+rockets, Toast.LENGTH_SHORT).show()
        }


    }
}