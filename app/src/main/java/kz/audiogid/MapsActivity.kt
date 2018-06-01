package kz.audiogid

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    public lateinit var mMap: GoogleMap
    private var TAG = "azat MapsActivity";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        val fb = FireBaseBD()
        fb.getData(mMap)

        enableMyLocation()
        mapClick()
    }

    private fun enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            android.Manifest.permission.ACCESS_FINE_LOCATION
        } else if (mMap != null) {
            mMap.isMyLocationEnabled = true
        }
    }

    private fun mapClick(){
        mMap.setOnMapClickListener {
            loop@for(i in 0 until AudioArr.size){
                AudioArr[i].pause()
            }
        }
    }
}
