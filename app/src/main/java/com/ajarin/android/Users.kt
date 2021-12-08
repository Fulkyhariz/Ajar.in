package com.ajarin.android

import java.io.Serializable

class Users (nama : String, email: String, password : String, phone : String, tutor : String) : Serializable {

    constructor() : this("", "", "", "", "") {

    }
}