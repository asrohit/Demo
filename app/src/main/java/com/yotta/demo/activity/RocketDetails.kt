package com.yotta.demo.activity

import android.app.Dialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.yotta.demo.R
import com.yotta.demo.constants.ConstantsApi
import com.yotta.demo.model.RocketInfo
import com.yotta.demo.model.Rockets
import com.yotta.demo.network.RocketApi
import okhttp3.Call
import okhttp3.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RocketDetails : AppCompatActivity() {
    private var mProgressDialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rocket_details)

        getRocketData(id)
    }

    private fun getRocketData(id: String) {
        if (ConstantsApi.isNetworkAvailable(this)) {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(ConstantsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service: RocketApi =  retrofit.create<RocketInfo>(Rockets::class.java)

            val listCall: Call<RocketInfo> = service.getRocketInfoDetails(latitude)

            showCustomProgressDialog()

            listCall.enqueue(object : Callback<RocketInfo> {
                @RequiresApi(Build.VERSION_CODES.N)
                fun onResponse(response: Response<RocketInfo>?, retrofit: Retrofit?) {
                    if (response.isSuccess) {
                        hideProgressDialog()
                        val weatherList: RocketInfo = response?.body()!!

                        val weatherResponseJsonString = Gson().toJson(weatherList)

                        setUpUI()
                        Log.e("onResponse: ", "" + weatherList)
                    } else {
                        val rc = response.code()
                    }
                }

                override fun onFailure(t: Throwable?) {
                    hideProgressDialog()
                    Log.e("Error", "onFailure: ")
                }
            })
        } else {
            Toast.makeText(
                this@RocketDetails,
                "No internet connection available",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showCustomProgressDialog() {
        mProgressDialog = Dialog(this)
        mProgressDialog!!.setContentView(R.layout.dialog_custom_progress)
        mProgressDialog!!.show()
    }

    private fun hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
        }
    }
}