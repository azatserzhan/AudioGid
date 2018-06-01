package kz.audiogid

import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import com.google.android.gms.maps.GoogleMap
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase



var AudioArr = mutableListOf<MediaPlayer>();

@Suppress("DEPRECATION")
class FireBaseBD {

    private var TAG = "azat FB"


    fun getData(googleMap: GoogleMap) {
        var data = Data();

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("data")

        myRef.addChildEventListener(object : ChildEventListener {

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                var item = dataSnapshot.getValue(Item::class.java)
                var text = item.apply { this!!.getItem(data) };

                //Marker
                val m = Marker();
                m.add(googleMap, item!!.name, item.value, item.audio, item.img, item.dolgota, item.shirota);
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }

    fun setData() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("data")
        myRef.push().setValue("asd");
    }

    fun getAudio(url: String) {
        loop@for(i in 0 until AudioArr.size){
            AudioArr[i].pause()
        }

        try{
            val AUDIO = MediaPlayer();
            AUDIO.setAudioStreamType(AudioManager.STREAM_MUSIC)

            AUDIO.setDataSource(url)
            AUDIO.prepareAsync()
            AUDIO.setOnPreparedListener(MediaPlayer.OnPreparedListener { player -> player.start() })
            AudioArr.add( AUDIO );
        }
        catch (e: NumberFormatException){}
    }

}


