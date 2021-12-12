package com.ajarin.android.`object`

import java.io.Serializable

class DataHistory(val orderId : String, val orderDate : String, val orderStatus : String, val subjectName : String,
                  val tutorName : String, val tutorId : String, val totalPrice : String) : Serializable{
}