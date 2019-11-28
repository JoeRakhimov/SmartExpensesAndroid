package com.ipakyulibank.mobile.util.permissions

import android.Manifest
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

class MyPermissionCheckerImpl(var permissionUtil: PermissionUtil): MyPermissionChecker {

    override fun checkCoarseLocationPermission(activity: AppCompatActivity?, listener: MyPermissionListener) {
        permissionUtil.checkPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION, listener)
    }

    override fun checkFineLocationPermission(activity: Activity?, listener: MyPermissionListener) {
        permissionUtil.checkPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION, listener)
    }

    override fun checkWriteExternalStoragePermission(
        activity: Activity?,
        listener: MyPermissionListener) {
        permissionUtil.checkPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, listener)
    }

}