package com.example.randomjoke

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.randomjoke.api.ApiInterface
import com.example.randomjoke.api.RetrofitClient
import com.example.randomjoke.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also{
            setContentView(it.root)
        }
        updateJoke()

        binding.newJokeBtn.setOnClickListener { updateJoke() }
    }
    fun updateJoke(){
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)

        lifecycleScope.launch{
            try {
                val response = apiInterface.getJoke()
                if(response.isSuccessful) {
                    binding.tvJoke.text = response.body()?.joke.toString()
                    Log.d("TEST", response.toString())
                }
                else{
                    Toast.makeText(this@MainActivity, response.errorBody().toString(), Toast.LENGTH_SHORT).show()
                }
            }
            catch (e: Exception){
                Log.e("TEST", e.localizedMessage as String)
            }
        }
    }
}