package com.cookandroid.teamproject1.id.model

import java.io.Serializable

data class CreateAccountData (
    var idText:String,
    var pwText:String,
    var nicknameText:String,
    var pNumText:String
    ) : Serializable
