package com.ajarin.android.`object`

import javax.security.auth.Subject

class HistoryObject {
    var orderId: String ?=null
    var orderDate: String ?=null
    var orderStatus: String ?=null
    var subjectName: String ?=null
    var tutorName: String ?=null
    var tutorId: String ?=null
    var totalPrice: String ?=null

    constructor(id : String, date : String, status : String, subject: String, tutor : String, tutId : String, price :String){
        orderId = id
        orderStatus = status
        orderDate = date
        subjectName = subject
        tutorName = tutor
        tutorId = tutId
        totalPrice = price
    }
}