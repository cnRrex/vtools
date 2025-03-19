package com.omarea.shell_utils

import android.content.Context
import android.os.Build
import com.omarea.common.shared.FileWrite.getPrivateFilePath
import com.omarea.common.shared.FileWrite.writePrivateFile
import com.omarea.vtools.R
import java.io.File
import java.util.*

class ToyboxIntaller(private val context: Context) {
    public fun install() : String {

        // Rrex: update toybox from https://landley.net/bin/toybox/latest/

        val installPath: String = context.getString(R.string.toolkit_install_path)
        val os_abi = System.getProperty("os.arch")
        val toybox_file_name = when (os_abi) {
            "aarch64" -> "toybox-aarch64"
            "armv7l" -> "toybox-armv7l"
            "x86_64" -> "toybox-x86_64"
            "i686" -> "toybox-i686"
            else -> "toybox-armv7l" // fallback to arm for other arch
        }
        val toyboxInstallPath = "$installPath/$toybox_file_name"
        val outsideToybox = getPrivateFilePath(context, toyboxInstallPath)

        if (!File(outsideToybox).exists()) {
            writePrivateFile(context.getAssets(), toyboxInstallPath, toyboxInstallPath, context)
        }

        return outsideToybox
    }
}