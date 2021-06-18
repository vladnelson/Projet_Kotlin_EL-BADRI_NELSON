package com.example.skyder.view.ui.home

import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skyder.R
import com.example.skyder.service.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var adapter: WeatherAdapter? = null
    var rv_home: RecyclerView? = null
    var progress_home: ProgressBar? = null
    var tv_weather_title: TextView? = null
    var tv_weather_item_body: TextView? = null
    var tv_hour_current: TextView? = null
    var img_delete: ImageView? = null

    var locationManager: LocationManager? = null
    var location: Location? = null
    var provider: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)


        rv_home = root.findViewById<RecyclerView>(R.id.rcv_home)
        progress_home = root.findViewById<ProgressBar>(R.id.progress_home)
        tv_weather_title = root.findViewById<TextView>(R.id.tv_weather_title)
        tv_weather_item_body = root.findViewById<TextView>(R.id.tv_weather_item_body)
        tv_hour_current = root.findViewById<TextView>(R.id.tv_hour_current)
        img_delete = root.findViewById<ImageView>(R.id.img_delete)


        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        executeCall()
        return root
    }


    private fun executeCall() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            try {
                progress_home?.visibility = View.VISIBLE

                delay(2000)


                locationManager =context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

                var criteria = Criteria()

                provider = locationManager?.getBestProvider(criteria, false)



                if ((ActivityCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                            || ActivityCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED)
                ) {
                    Log.d("HomeRepository", provider!!)

                    val loclistener: LocationListener = object : LocationListener {
                        override fun onLocationChanged(l: Location) {

                        }
                        override fun onProviderEnabled(p: String) {

                        }
                        override fun onProviderDisabled(p: String) {

                        }
                        override fun onStatusChanged(p: String, status: Int, extras: Bundle) {

                        }
                    }
                    locationManager
                        ?.requestLocationUpdates(provider!!, 1, 0.1f, loclistener)

                    location = locationManager?.getLastKnownLocation(provider!!)
                }else  {
                    requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
                }
                Log.d("HomeRepository", location.toString())
                if (location != null) {

                    var lat: Double? = location?.latitude
                    var lng: Double? = location?.longitude

                    Log.d("HomeRepository", "Position Actuelle : lat= ${lat}, long= ${lng}")

                } else {
                    Log.d("HomeRepository", "No provider")
                }

                val response = ApiClient.apiService.GetweatherCurrent("Paris")

                if (response.isSuccessful && response.body() != null) {

                    val content = response.body()

                    if (response.code() == 200 && content != null) {
                        Log.d("HomeRepository", "Get Weather Current Response + 200")
                        Log.d("HomeRepository", content.toString())

                        tv_weather_title?.text =
                            " " + content.city_info.name + " (" + content.city_info.country + ")"
                        tv_weather_item_body?.text = content.current_condition.tmp + "°"

                        tv_hour_current?.text = content.current_condition.hour

                        Glide.with(this@HomeFragment.context)
                            .load(content.current_condition.icon_big).into(
                                img_delete!!
                            )
                        img_delete?.visibility = View.VISIBLE

                        /*
                        Toast.makeText(
                            this@HomeFragment.context,
                            "OK it s good",
                            Toast.LENGTH_LONG
                        ).show()
                        */

                    } else {

                    }

                } else {
                    Toast.makeText(
                        this@HomeFragment.context,
                        "Error Occurred: ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } catch (e: Exception) {
                Toast.makeText(
                    this@HomeFragment.context,
                    "Error Occurred: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }

            progress_home?.visibility = View.GONE
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            1 -> {

                if ((ActivityCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                            || ActivityCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED)
                ) {

                    Toast.makeText(
                        this@HomeFragment.context,
                        "Permission Granted",
                        Toast.LENGTH_SHORT
                    ).show()
                    executeCall()
                } else {
                    requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
                    Toast.makeText(
                        this@HomeFragment.context,
                        "Permission Denied",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }
}