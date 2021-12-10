package com.ajarin.android.`object`

class TutorObject {
    var nama: String = ""
    var description: String = ""
    var emailtutor: String = ""
    var pendidikan: String = ""
    var subject: String = ""
    var tarif: String = ""
    var linkedin: String = ""
    var notelp: String = ""
    var whatsapp: String = ""
    var rating : String = ""

    constructor(nama : String, rating : String, tutorSubject : String){
        this.nama = nama
        this.rating = rating
        this.subject = tutorSubject
    }

    constructor(nama : String, rating : String, subject : String, tarif : String, emailTutor : String, ){
        this.nama = nama
        this.rating = rating
        this.subject = subject
        this.tarif = tarif
        this.emailtutor = emailTutor
    }
}