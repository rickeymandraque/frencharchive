package com.lagradost
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.getQualityFromName



open class UqloadExtractor : ExtractorApi() {
    override var name = "Uqload"
    override var mainUrl = "https://uqload.to"
    override val requiresReferer = false

    // AhAh j'ai la logique mais pas le temps de faire le code soon
    override suspend fun getUrl(url: String, referer: String?): List<ExtractorLink>? {
        val response0 = app.get(url).text

        return null;
    }
}