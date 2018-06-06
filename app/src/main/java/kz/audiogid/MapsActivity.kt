package kz.audiogid

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.SeekBar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var mMap: GoogleMap

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
        audioSeekBar()
    }

    private fun enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            android.Manifest.permission.ACCESS_FINE_LOCATION
        } else if (mMap != null) {
            mMap.isMyLocationEnabled = true
        }
    }

    private fun mapClick(){
        mMap.setOnMapClickListener (){
            loop@for(i in 0 until AudioArr.size) {
                AudioArr[i].pause()
            }
            isPlay = false
        }

    }


    var mySeekBar:SeekBar? = null;
    fun audioSeekBar(){
        var audio:MediaPlayer? = null;
        mySeekBar = findViewById(R.id.seekBar);

        mySeekBar!!.setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                        if (fromUser) {
                            try {
                                val mySound = Sound()
                                mySound.stopAllSound()

                                audio = AudioArr[ AudioArr.size-1 ];
                                isPlay = true

                                seekMax = audio!!.duration
                                seekCount = progress
                                mySeekBar!!.setMax( seekMax )

                                audio!!.start()
                                audio!!.seekTo(progress)
                                mySeekBar!!.setProgress( progress )
                            }catch (e: Exception){
                                Log.d("azat ERROR SEEK", AudioArr.size.toString() )
                            }
                        }
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar) {

                    }
                }
        )

        setTimeout()
    }


    var seekMax:Int = 0
    var seekTemp: IntArray = intArrayOf(0, 1);
    fun setTimeout(){
        var myAudio: MediaPlayer;

        Handler().postDelayed({
            if(seekCount<seekMax && isPlay){
                mySeekBar!!.setProgress(seekCount)
                seekCount = seekCount+1000
            }

            try {
                if( AudioArr != null ){
                    myAudio = AudioArr[ AudioArr.size - 1 ]
                    seekMax = myAudio!!.duration
                    mySeekBar!!.setMax( seekMax )
                    mySeekBar!!.setProgress( seekCount )

                    seekTemp[0] = AudioArr.size
                    if( seekTemp[0]!=seekTemp[1] ){
                        seekCount = 0
                        seekTemp[1] = seekTemp[0]
                    }
                }
            }catch (e: ArrayIndexOutOfBoundsException){}
            return@postDelayed setTimeout()
        }, 1000)
    }
}
