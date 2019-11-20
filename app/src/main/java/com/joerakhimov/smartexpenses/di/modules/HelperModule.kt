package com.joerakhimov.smartexpenses.di.modules

import com.ipakyulibank.mobile.util.permissions.MyPermissionChecker
import com.ipakyulibank.mobile.util.permissions.MyPermissionCheckerImpl
import com.ipakyulibank.mobile.util.permissions.PermissionUtil
import dagger.Module
import dagger.Provides

@Module
class HelperModule {

    @Provides
    fun providePermissionUtil() = PermissionUtil()

    @Provides
    fun providePermissionChecker(permissionUtil: PermissionUtil): MyPermissionChecker = MyPermissionCheckerImpl(permissionUtil)

}