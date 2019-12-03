package com.ipakyulibank.mobile.data.preferences



interface Prefs {

    fun setToken(token: String?)
    fun getToken(): String?
    fun removeToken()
    fun isUserLogged(): Boolean?

    fun setEmail(email: String?)
    fun getEmail(): String?
    fun removeEmail()
    fun setLatestSpendingsAmount(amount: Int)
    fun getLatestSpendingsAmount(): Int

}