package com.ajarin.android.`object`

class TopicsObject {
    var topicName: String ?=null
    var subjectName: String ?=null
    var subjectDesc: String ?=null

    constructor(topname: String, subname : String, desc : String){
        topicName = topname
        subjectName = subname
        subjectDesc = desc
    }
}