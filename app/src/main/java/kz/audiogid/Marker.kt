package kz.audiogid

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class Marker: GoogleMap.OnMarkerClickListener {


    val mySound = Sound()

    private var mDarwin1: Marker? = null

    override fun onMarkerClick(myMarker: Marker?): Boolean {

        val title = myMarker!!.title.toString();

        loop@for( i in 0 until name.size){
            if( title == name[i] ){
                isPlay = true
                mySound.getAudio( audio[i] )
                seekCount = 0
                break
            }
        }
        return false;
    }

    fun add( googleMap: GoogleMap, name: String, value: String, audio: String, img: String, dolgota: Double, shirota: Double){

        val sydney = LatLng(dolgota, shirota)

        mDarwin1 = googleMap.addMarker(MarkerOptions()
                .position(sydney)
                .title(name)
                .snippet(value)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
        )

        googleMap.setOnMarkerClickListener(this);
    }



}