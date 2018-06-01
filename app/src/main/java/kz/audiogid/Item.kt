package kz.audiogid

class Item {
    var name: String = ""
    var value: String = ""
    var audio: String = ""
    var img: String = ""
    var dolgota: Double = 0.0
    var shirota: Double = 0.0

    constructor() {
    }

    constructor(name: String, value: String, audio: String, img: String, dolgota: Double, shirota: Double) {
        this.name = name
        this.value = value
        this.audio = audio
        this.img = img
        this.dolgota = dolgota;
        this.shirota = shirota;
    }


    fun getItem(data: Data) {
        data.addData( this.name, this.value, this.audio, this.img, this.dolgota, this.shirota);
    }
}