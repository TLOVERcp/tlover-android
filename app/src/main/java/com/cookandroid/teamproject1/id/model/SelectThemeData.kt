package com.cookandroid.teamproject1.id.model

import java.io.Serializable

data class SelectThemeData (
    var idText:String,
    var pwText:String,
    var nicknameText:String,
    var pNumText:String,
    var destArray:ArrayList<String>,
    var themeArray:ArrayList<String>
) : Serializable