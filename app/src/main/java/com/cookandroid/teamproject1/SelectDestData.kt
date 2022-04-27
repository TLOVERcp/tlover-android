package com.cookandroid.teamproject1

import java.io.Serializable

data class SelectDestData (
    var idText:String,
    var pwText:String,
    var nicknameText:String,
    var pNumText:String,
    var destArray:ArrayList<String>
    ) : Serializable
