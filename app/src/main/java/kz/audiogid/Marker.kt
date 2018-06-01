package kz.audiogid

import android.util.Log
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions




class Marker: GoogleMap.OnMarkerClickListener {


    val fb = FireBaseBD()

    private var mDarwin1: Marker? = null

    override fun onMarkerClick(myMarker: Marker?): Boolean {

        val title = myMarker!!.title.toString();

        loop@for( i in 0 until name.size){
            if( title == name[i] ){
                Log.d("azat Marker", name[i])

                fb.getAudio( audio[i] )
            }
        }

        return false;
    }

    fun add( googleMap: GoogleMap, name: String, value: String, audio: String, img: String, dolgota: Double, shirota: Double){

        val sydney = LatLng(dolgota, shirota)

        /*googleMap.addMarker(MarkerOptions()
                .position(sydney)
                .title(name)
                .snippet(value)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.audio))
        )*/


        mDarwin1 = googleMap.addMarker(MarkerOptions()
                .position(sydney)
                .title(name)
                .snippet(value)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.audio))
        )

        googleMap.setOnMarkerClickListener(this);
    }



}