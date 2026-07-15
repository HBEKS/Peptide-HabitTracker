package com.ubayadev.peptideuts.util

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException



//UDH GA KEPAKE DIGANTI PAKE ROOM

class FileHelper(val context: Context) {
    val folderName = "habit_folder"
    val fileName = "habits.json"

    private fun getFile(): File {
        val dir = File(context.filesDir, folderName)
        if (!dir.exists()) {
            dir.mkdirs() // bikin folder jika folder belum ada
        }
        return File(dir, fileName)
    }

    fun writeToFile(data: String) {
        try {
            val file = getFile()
            FileOutputStream(file, false).use { output ->
                output.write(data.toByteArray())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    fun readFromFile(): String {
        return try {
            val file = getFile()
            if (!file.exists()) {
                return ""
            }
            file.bufferedReader().useLines { lines ->
                lines.joinToString("\n")
            }
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }
    fun deleteFile(): Boolean {
        return getFile().delete()
    }

    fun getFilePath(): String {
        return getFile().absolutePath
    }
}