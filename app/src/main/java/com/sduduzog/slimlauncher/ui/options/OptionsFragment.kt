package com.sduduzog.slimlauncher.ui.options

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.Navigation
import com.sduduzog.slimlauncher.R
import com.sduduzog.slimlauncher.ui.dialogs.ChangeThemeDialog
import com.sduduzog.slimlauncher.ui.dialogs.ChooseTimeFormatDialog
import com.sduduzog.slimlauncher.utils.BaseFragment
import kotlinx.android.synthetic.main.options_fragment.*

class OptionsFragment : BaseFragment() {
    override fun getFragmentView(): ViewGroup = options_fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.options_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupLaunchers()
        setupDialogs()
        setupPreferences()
        val destinationId = R.id.action_optionsFragment_to_customiseAppsFragment
        options_fragment_customise_apps
                .setOnClickListener(Navigation.createNavigateOnClickListener(destinationId))
    }

    private fun setupLaunchers() {
        options_fragment_about_slim.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.slim_url)))
            launchActivity(it, intent)
        }
        options_fragment_device_settings.setOnClickListener {
            val intent = Intent(Settings.ACTION_SETTINGS)
            launchActivity(it, intent)
        }
        options_fragment_device_settings.setOnLongClickListener {
            val intent = Intent(Settings.ACTION_HOME_SETTINGS)
            launchActivity(it, intent)
            true
        }
    }

    private fun setupDialogs() {
        options_fragment_change_theme.setOnClickListener {
            val changeThemeDialog = ChangeThemeDialog.getThemeChooser()
            changeThemeDialog.showNow(fragmentManager, "THEME_CHOOSER")
        }
        options_fragment_choose_time_format.setOnClickListener {
            val chooseTimeFormatDialog = ChooseTimeFormatDialog.getInstance()
            chooseTimeFormatDialog.showNow(fragmentManager, "TIME_FORMAT_CHOOSER")
        }
    }

    private fun setupPreferences() {
        val settings = context!!.getSharedPreferences(getString(R.string.prefs_settings), MODE_PRIVATE)
        options_fragment_toggle_status_bar.setOnClickListener {
            val hidden = settings.getBoolean(getString(R.string.prefs_settings_key_toggle_status_bar), false)
            settings.edit {
                putBoolean(getString(R.string.prefs_settings_key_toggle_status_bar), !hidden)
            }
        }
    }
}