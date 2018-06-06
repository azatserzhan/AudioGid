package kz.audiogid

var name = mutableListOf<String>();
var value = mutableListOf<String>();
var audio = mutableListOf<String>();
var img = mutableListOf<String>();
var dolgota = mutableListOf<Double>();
var shirota = mutableListOf<Double>();

class Data{

    fun addData(newName: String, newValue: String, newAudio: String, newImg: String, newDolgota: Double, newShirota: Double) {
        name.add(newName)
        value.add(newValue)
        audio.add(newAudio)
        img.add(newImg)
        dolgota.add(newDolgota)
        shirota.add(newShirota)
    }
}