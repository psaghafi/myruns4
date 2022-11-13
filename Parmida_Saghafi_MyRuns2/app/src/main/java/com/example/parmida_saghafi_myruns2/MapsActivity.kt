package com.example.parmida_saghafi_myruns2

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.parmida_saghafi_myruns2.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener,
    GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener  {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val PERMISSION_REQUEST_CODE = 0
    private lateinit var locationManager: LocationManager

    private var mapCentered = false
    private lateinit var  markerOptions: MarkerOptions
    private lateinit var  polylineOptions: PolylineOptions
    private lateinit var  polylines: ArrayList<Polyline>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.setOnMapClickListener(this)
        mMap.setOnMapLongClickListener(this)
        polylineOptions = PolylineOptions()
        polylineOptions.color(Color.BLACK)
        polylines = ArrayList()
        markerOptions = MarkerOptions()

        checkPermission()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (locationManager != null)
            locationManager.removeUpdates(this)
    }

    @SuppressLint("MissingPermission")
    fun initLocationManager() {
        try {
            locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            val criteria = Criteria()
            criteria.accuracy = Criteria.ACCURACY_FINE
            val provider : String? = locationManager.getBestProvider(criteria, true)
            if(provider != null) {
                val location = locationManager.getLastKnownLocation(provider)

                if (location != null)
                    onLocationChanged(location)
                locationManager.requestLocationUpdates(provider, 0, 0f, this)
            }
        } catch (e: SecurityException) {
        }
    }


    override fun onLocationChanged(location: Location) {
        println("debug: onlocationchanged()")
        val lat = location.latitude
        val lng = location.longitude
        val latLng = LatLng(lat, lng)
        if (!mapCentered) {
            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17f)
            mMap.animateCamera(cameraUpdate)
            markerOptions.position(latLng)
            mMap.addMarker(markerOptions)
            polylineOptions.add(latLng)
            mapCentered = true
        }
    }

    override fun onMapClick(latLng: LatLng) {
        for (i in polylines.indices) polylines[i].remove()
        polylineOptions.points.clear()
    }

    override fun onMapLongClick(latLng: LatLng) {
        markerOptions.position(latLng!!)
        mMap.addMarker(markerOptions)
        polylineOptions.add(latLng)
        polylines.add(mMap.addPolyline(polylineOptions))
    }

    fun checkPermission() {
        if (Build.VERSION.SDK_INT < 23) return
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_CODE)
        else
            initLocationManager()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) initLocationManager()
        }
    }
}