package com.ipakyulibank.mobile.util.permissions

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

interface MyPermissionChecker {

    fun checkCoarseLocationPermission(activity: AppCompatActivity?, listener: MyPermissionListener)

    fun checkFineLocationPermission(activity: Activity?, listener: MyPermissionListener)

}