
package com.lagradost

import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

@CloudstreamPlugin
class FrenchStreamProviderPlugin: Plugin() {
    val frenchStreamProvide = FrenchStreamProvider()
    override fun load(context: Context) {
        // All providers should be added in this manner. Please don't edit the providers list directly.
        registerMainAPI(frenchStreamProvide)
    }
    init{
        this.openSettings = {
            val activity = it as? AppCompatActivity
            if(activity != null){
                val frag = FrenchStreamSettingsFragment(this, frenchStreamProvide)
                frag.show(activity.supportFragmentManager, "change url")
            }
        }
    }
}
