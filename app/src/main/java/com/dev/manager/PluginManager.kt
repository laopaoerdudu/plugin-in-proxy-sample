package com.dev.manager

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import dalvik.system.DexClassLoader
import java.io.File

object PluginManager {
    private var dexClassLoader: DexClassLoader? = null
    private var resources: Resources? = null
    private var packageInfo: PackageInfo? = null

    fun loadPlugin(context: Context) {
        val path = File(context.getDir("plugin", Context.MODE_PRIVATE), "plugin.apk").absolutePath
        packageInfo =
            context.packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES)

        val dex = context.getDir("dex", Context.MODE_PRIVATE).absolutePath
        dexClassLoader = DexClassLoader(path, dex, null, context.classLoader)

        try {
            val manager = AssetManager::class.java.newInstance()
            val addAssetPathMethod =
                AssetManager::class.java.getMethod("addAssetPath", String::class.java)
            addAssetPathMethod.invoke(manager, path)
            resources = Resources(
                manager,
                context.resources.displayMetrics,
                context.resources.configuration
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun getDexClassLoader(): DexClassLoader? = dexClassLoader

    fun getResources(): Resources? = resources

    fun getPackageInfo(): PackageInfo? = packageInfo
}