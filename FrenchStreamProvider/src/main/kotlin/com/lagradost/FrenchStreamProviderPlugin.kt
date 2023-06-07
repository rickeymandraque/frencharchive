
package com.lagradost

import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

@CloudstreamPlugin
class FrenchStreamProviderPlugin: Plugin() {
    val frenchStreamProvide = FrenchStreamProvider()
    val frenchStreamSettingsFragment = FrenchStreamSettingsFragment(this, frenchStreamProvide)
    val sharedPreference =  frenchStreamSettingsFragment.activity?.getSharedPreferences("frenchstream",Context.MODE_PRIVATE)
    var editor = sharedPreference?.edit()
    override fun load(context: Context) {
        // All providers should be added in this manner. Please don't edit the providers list directly.
        registerMainAPI(frenchStreamProvide)
        if(sharedPreference?.contains("url") == true){
            sharedPreference.getString("url", "Error")?.let { frenchStreamProvide.changeUrl(it)}
        }
    }
    init{
        this.openSettings = {
            val activity = it as? AppCompatActivity
            if(activity != null){
                val frag = frenchStreamSettingsFragment
                frag.show(activity.supportFragmentManager, "change url")
            }
        }
    }
}
