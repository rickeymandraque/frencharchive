package com.lagradost
import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.utils.ExtractorApi
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.getQualityFromName



open class UqloadExtractor : ExtractorApi() {
    override var name = "Uqload"
    override var mainUrl = "https://uqload.to"
    override val requiresReferer = false
    override suspend fun getUrl(url: String, referer: String?): List<ExtractorLink>? {
        val response0 = app.get(url).text
        val md5 =mainUrl+(Regex("/pass_md5/[^']*").find(response0)?.value ?: return null)
        val trueUrl = app.get(md5, referer = url).text + "zUEJeL3mUN?token=" + md5.substringAfterLast("/")   //direct link to extract  (zUEJeL3mUN is random)
        val quality = Regex("\\d{3,4}p").find(response0.substringAfter("<title>").substringBefore("</title>"))?.groupValues?.get(0)
        return listOf(
            ExtractorLink(
                trueUrl,
                this.name,
                trueUrl,
                mainUrl,
                getQualityFromName(quality),
                false
            )
        ) // links are valid in 8h

    }
}