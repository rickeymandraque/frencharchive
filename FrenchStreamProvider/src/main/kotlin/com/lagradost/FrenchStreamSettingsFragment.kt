package com.lagradost

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import androidx.core.content.res.ResourcesCompat

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lagradost.cloudstream3.plugins.Plugin


class FrenchStreamSettingsFragment(
        private val plugin: Plugin,
        val frenchStreamProvider: FrenchStreamProvider
) :
    BottomSheetDialogFragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val id = plugin.resources!!.getIdentifier(
                "settings",
                "layout",
                BuildConfig.LIBRARY_PACKAGE_NAME
        )
        val layout = plugin.resources!!.getLayout(id)

        return inflater.inflate(layout, container, false)

    }

    private fun <T : View> View.findView(name: String): T {
        val id = plugin.resources!!.getIdentifier(name, "id", BuildConfig.LIBRARY_PACKAGE_NAME)
        return this.findViewById(id)
    }

    private fun getDrawable(name: String): Drawable? {
        val id =
                plugin.resources!!.getIdentifier(name, "drawable", BuildConfig.LIBRARY_PACKAGE_NAME)
        return ResourcesCompat.getDrawable(plugin.resources!!, id, null)
    }

    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        super.onViewCreated(view, savedInstanceState)
        val urlField = view.findView<EditText>("urlField")
        val saveBtn = view.findView<Button>("saveBtn")
        // Change Url
        if (sharedPref != null) {
            urlField.setText(sharedPref.getString("url", ""))
            sharedPref.getString("url", "")?.let { frenchStreamProvider.changeUrl(it) }
        }

        saveBtn.setOnClickListener(View.OnClickListener {
            frenchStreamProvider.changeUrl(urlField.text.toString())
            sharedPref?.edit()?.putString("url", urlField.text.toString())
        })
        
    }
}

