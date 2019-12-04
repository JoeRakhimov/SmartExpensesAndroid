package com.ipakyulibank.mobile.data.preferences

const val PREF_TOKEN = "token"
const val PREF_EMAIL = "email"
const val PREF_LATEST_SPENDINGS = "latest_spendings"
const val PREF_PHOTO_FILE_PATH = "photo_file_path"

const val DEFAULT_TOKEN = ""
const val DEFAULT_EMAIL = ""
const val DEFAULT_LATEST_SPENDINGS = 5
const val DEFAULT_PHOTO_FILE_PATH = ""

class PrefsImpl constructor(var prefsUtil: PrefsUtil) : Prefs {

    override fun setToken(token: String?) = prefsUtil.setString(PREF_TOKEN, token)
    override fun getToken() = prefsUtil.getString(PREF_TOKEN, DEFAULT_TOKEN)
    override fun removeToken() = prefsUtil.setString(PREF_TOKEN, DEFAULT_TOKEN)
    override fun isUserLogged() = getToken() != DEFAULT_TOKEN

    override fun setEmail(email: String?)= prefsUtil.setString(PREF_EMAIL, email)
    override fun removeEmail() = prefsUtil.setString(PREF_EMAIL, DEFAULT_EMAIL)
    override fun getEmail()= prefsUtil.getString(PREF_EMAIL, DEFAULT_EMAIL)
    override fun getLatestSpendingsAmount() = prefsUtil.getInt(PREF_LATEST_SPENDINGS, DEFAULT_LATEST_SPENDINGS)
    override fun setLatestSpendingsAmount(amount: Int) = prefsUtil.setInt(PREF_LATEST_SPENDINGS, amount)
    override fun setPhotoFilePath(path: String?) = prefsUtil.setString(PREF_PHOTO_FILE_PATH, path)
    override fun getPhotoFilePath() = prefsUtil.getString(PREF_PHOTO_FILE_PATH, DEFAULT_PHOTO_FILE_PATH)
}