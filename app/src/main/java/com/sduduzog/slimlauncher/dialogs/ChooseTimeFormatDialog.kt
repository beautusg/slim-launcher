package com.sduduzog.slimlauncher.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.core.content.edit
import androidx.fragment.app.DialogFragment
import com.sduduzog.slimlauncher.R

class ChooseTimeFormatDialog private constructor() : DialogFragment() {

    private lateinit var settings: SharedPreferences

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        settings = context!!.getSharedPreferences(getString(R.string.prefs_settings), MODE_PRIVATE)

        val is24Hour = settings.getBoolean(getString(R.string.prefs_settings_key_time_format), true)
        val index = if (is24Hour) 1 else 0
        builder.setTitle(R.string.choose_time_format_dialog_title)
        builder.setSingleChoiceItems(R.array.time_format_array, index) { dialogInterface, i ->
            dialogInterface.dismiss()
            settings.edit {
                putBoolean(getString(R.string.prefs_settings_key_time_format), i != 0)
            }

        }
        return builder.create()
    }


    companion object {
        fun getInstance(): ChooseTimeFormatDialog = ChooseTimeFormatDialog()
    }
}