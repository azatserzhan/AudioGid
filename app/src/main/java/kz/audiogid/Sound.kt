package kz.audiogid

import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log

var seekCount:Int = 0
var isPlay = true
class Sound{
    fun getAudio(url: String) {
        stopAllSound()

        try{
            var checked = false;

            if( AudioArrHref.size > 0 ){
                loop@for( i in 0 until AudioArrHref.size ){
                    if( url == AudioArrHref[i] ){
                        //Запускаю заранее скачанное аудио
                        stopAllSound()
                        AudioArr[i].start()
                        checked = true
                    }
                }

                if( checked == false ){
                    //скачиваю из FireBase и запускаю
                    getWidthUrl(url)
                }
            }else{
                getWidthUrl(url)
            }
        }
        catch (e: NumberFormatException){}


    }

    private fun getWidthUrl(url: String) {
        try{
            val AUDIO = MediaPlayer();
            AUDIO.setAudioStreamType(AudioManager.STREAM_MUSIC)

            AUDIO.setDataSource(url)
            AUDIO.prepareAsync()
            AUDIO.setOnPreparedListener(MediaPlayer.OnPreparedListener { player -> player.start() })

            AudioArr.add( AUDIO )
            AudioArrHref.add(url)

            Log.d("azat URL", url)
        }catch (e: Exception){
        }

    }

    fun stopAllSound(){
        loop@for(i in 0 until AudioArr.size){
            AudioArr[i].pause()
        }
    }
}