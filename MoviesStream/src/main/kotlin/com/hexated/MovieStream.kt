package com.hexated

import com.fasterxml.jackson.annotation.JsonProperty
import com.hexated.MovieExtractor.invokeAnimes
import com.hexated.MovieExtractor.invokeAsk4Movies
import com.hexated.MovieExtractor.invokeBlackmovies
import com.hexated.MovieExtractor.invokeBollyMaza
import com.hexated.MovieExtractor.invokeCodexmovies
import com.hexated.MovieExtractor.invokeCryMovies
import com.hexated.MovieExtractor.invokeDbgo
import com.hexated.MovieExtractor.invokeFilmxy
import com.hexated.MovieExtractor.invokeHDMovieBox
import com.hexated.MovieExtractor.invokeIdlix
import com.hexated.MovieExtractor.invokeKimcartoon
import com.hexated.MovieExtractor.invokeMovieHab
import com.hexated.MovieExtractor.invokeNoverse
import com.hexated.MovieExtractor.invokeVidSrc
import com.hexated.MovieExtractor.invokeXmovies
import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.LoadResponse.Companion.addTrailer
import com.lagradost.cloudstream3.metaproviders.TmdbProvider
import com.hexated.MovieExtractor.invokeDahmerMovies
import com.hexated.MovieExtractor.invokeDreamfilm
import com.hexated.MovieExtractor.invokeEdithxmovies
import com.hexated.MovieExtractor.invokeFDMovies
import com.hexated.MovieExtractor.invokeFlixon
import com.hexated.MovieExtractor.invokeFmovies
import com.hexated.MovieExtractor.invokeFwatayako
import com.hexated.MovieExtractor.invokeGMovies
import com.hexated.MovieExtractor.invokeGdbotMovies
import com.hexated.MovieExtractor.invokeGoku
import com.hexated.MovieExtractor.invokeGomovies
import com.hexated.MovieExtractor.invokeJmdkhMovies
import com.hexated.MovieExtractor.invokeKisskh
import com.hexated.MovieExtractor.invokeLing
import com.hexated.MovieExtractor.invokeM4uhd
import com.hexated.MovieExtractor.invokeMovie123Net
import com.hexated.MovieExtractor.invokeMoviesbay
import com.hexated.MovieExtractor.invokeMoviezAdd
import com.hexated.MovieExtractor.invokeNavy
import com.hexated.MovieExtractor.invokeNinetv
import com.hexated.MovieExtractor.invokeNowTv
import com.hexated.MovieExtractor.invokePutlocker
import com.hexated.MovieExtractor.invokeRStream
import com.hexated.MovieExtractor.invokeRidomovies
import com.hexated.MovieExtractor.invokeRubyMovies
import com.hexated.MovieExtractor.invokeShinobiMovies
import com.hexated.MovieExtractor.invokeShivamhw
import com.hexated.MovieExtractor.invokeSmashyStream
import com.hexated.MovieExtractor.invokeDumpStream
import com.hexated.MovieExtractor.invokeEmovies
import com.hexated.MovieExtractor.invokeFourCartoon
import com.hexated.MovieExtractor.invokePobmovies
import com.hexated.MovieExtractor.invokeTvMovies
import com.hexated.MovieExtractor.invokeUhdmovies
import com.hexated.MovieExtractor.invokeVitoenMovies
import com.hexated.MovieExtractor.invokeWatchOnline
import com.hexated.MovieExtractor.invokeWatchsomuch
import com.lagradost.cloudstream3.extractors.VidSrcExtractor
import com.lagradost.cloudstream3.utils.AppUtils.parseJson
import com.lagradost.cloudstream3.utils.AppUtils.toJson
import com.lagradost.cloudstream3.utils.ExtractorLink
import kotlin.math.roundToInt

open class MovieStream : TmdbProvider() {
    override var name = "SoraStream"
    override val hasMainPage = true
    override val instantLinkLoading = true
    override val useMetaLoadResponse = true
    override val hasQuickSearch = true
    override val supportedTypes = setOf(
        TvType.Movie,
        TvType.TvSeries,
        TvType.Anime,
    )

    /** AUTHOR : Hexated & Sora */
    companion object {
        /** TOOLS */
        private const val tmdbAPI = "https://api.themoviedb.org/3"
        const val gdbot = "https://gdtot.pro"
        const val anilistAPI = "https://graphql.anilist.co"
        const val malsyncAPI = "https://api.malsync.moe"
        const val consumetHelper = "https://api.consumet.org/anime/9anime/helper"

        private val apiKey =
            base64DecodeAPI("ZTM=NTg=MjM=MjM=ODc=MzI=OGQ=MmE=Nzk=Nzk=ZjI=NTA=NDY=NDA=MzA=YjA=") // PLEASE DON'T STEAL

        /** ALL SOURCES */
        const val vidSrcAPI = "https://v2.vidsrc.me"
        const val dbgoAPI = "https://dbgo.fun"
        const val movieHabAPI = "https://moviehab.com"
        const val hdMovieBoxAPI = "https://hdmoviebox.net"
        const val dreamfilmAPI = "https://dreamfilmsw.net"
        const val series9API = "https://series9.sh"
        const val idlixAPI = "https://idlixian.com"
        const val noverseAPI = "https://www.nollyverse.com"
        const val uniqueStreamAPI = "https://uniquestream.net"
        const val filmxyAPI = "https://www.filmxy.vip"
        const val kimcartoonAPI = "https://kimcartoon.li"
        const val xMovieAPI = "https://xemovies.to"
        const val zoroAPI = "https://kaido.to"
        const val crunchyrollAPI = "https://beta-api.crunchyroll.com"
        const val kissKhAPI = "https://kisskh.co"
        const val lingAPI = "https://ling-online.net"
        const val uhdmoviesAPI = "https://uhdmovies.life"
        const val fwatayakoAPI = "https://5100.svetacdn.in"
        const val gMoviesAPI = "https://gdrivemovies.xyz"
        const val fdMoviesAPI = "https://freedrivemovie.lol"
        const val m4uhdAPI = "https://m4uhd.tv"
        const val tvMoviesAPI = "https://www.tvseriesnmovies.com"
        const val moviezAddAPI = "https://ww2.moviezaddiction.click"
        const val bollyMazaAPI = "https://m.bollymaza.click"
        const val moviesbayAPI = "https://moviesbay.live"
        const val rStreamAPI = "https://remotestre.am"
        const val flixonAPI = "https://flixon.lol"
        const val animeKaizokuAPI = "https://animekaizoku.com"
        const val movie123NetAPI = "https://ww8.0123movie.net"
        const val smashyStreamAPI = "https://embed.smashystream.com"
        const val watchSomuchAPI = "https://watchsomuch.tv" // sub only
        val gomoviesAPI = base64DecodeAPI("bQ==Y28=ZS4=aW4=bmw=LW8=ZXM=dmk=bW8=Z28=Ly8=czo=dHA=aHQ=")
        const val ask4MoviesAPI = "https://ask4movie.net"
        const val biliBiliAPI = "https://api-vn.kaguya.app/server"
        const val watchOnlineAPI = "https://watchonline.ag"
        const val nineTvAPI = "https://api.9animetv.live"
        const val putlockerAPI = "https://ww7.putlocker.vip"
        const val fmoviesAPI = "https://fmovies.to"
        const val nowTvAPI = "https://myfilestorage.xyz"
        const val gokuAPI = "https://goku.sx"
        const val ridomoviesAPI = "https://ridomovies.pw"
        const val navyAPI = "https://navy-issue-i-239.site"
        const val emoviesAPI = "https://emovies.si"
        const val pobmoviesAPI = "https://pobmovies.cam"
        const val fourCartoonAPI = "https://4cartoon.net"

        // INDEX SITE
        const val blackMoviesAPI = "https://dl.blacklistedbois.workers.dev/0:"
        const val codexMoviesAPI = "https://packs.codexcloudx.tech/0:"
        const val edithxMoviesAPI = "https://index.edithx.ga/0:"
        const val dahmerMoviesAPI = "https://edytjedhgmdhm.abfhaqrhbnf.workers.dev"
        const val jmdkhMovieAPI = "https://tg.jmdkh.eu.org/0:"
        const val rubyMovieAPI = "https://upload.rubyshare111.workers.dev/0:"
        const val shinobiMovieAPI = "https://home.shinobicloud.cf/0:"
        const val vitoenMovieAPI = "https://openmatte.vitoencodes.workers.dev/0:"
        const val shivamhwAPI = "https://foogle.shivamhw.me"
        val cryMoviesAPI =
            base64DecodeAPI("ZXY=LmQ=cnM=a2U=b3I=Lnc=ZXI=ZGQ=bGE=cy0=b2I=YWM=Lmo=YWw=aW4=LWY=cm4=Ym8=cmU=Ly8=czo=dHA=aHQ=")

        fun getType(t: String?): TvType {
            return when (t) {
                "movie" -> TvType.Movie
                else -> TvType.TvSeries
            }
        }

        fun getStatus(t: String?): ShowStatus {
            return when (t) {
                "Returning Series" -> ShowStatus.Ongoing
                else -> ShowStatus.Completed
            }
        }

        fun base64DecodeAPI(api: String): String {
            return api.chunked(4).map { base64Decode(it) }.reversed().joinToString("")
        }

    }

    override val mainPage = mainPageOf(
        "$tmdbAPI/trending/all/day?api_key=$apiKey&region=US" to "Trending",
        "$tmdbAPI/movie/popular?api_key=$apiKey&region=US" to "Popular Movies",
        "$tmdbAPI/tv/popular?api_key=$apiKey&region=US&with_original_language=en" to "Popular TV Shows",
        "$tmdbAPI/tv/airing_today?api_key=$apiKey&region=US&with_original_language=en" to "Airing Today TV Shows",
//        "$tmdbAPI/tv/on_the_air?api_key=$apiKey&region=US" to "On The Air TV Shows",
        "$tmdbAPI/discover/tv?api_key=$apiKey&with_networks=213" to "Netflix",
        "$tmdbAPI/discover/tv?api_key=$apiKey&with_networks=1024" to "Amazon",
        "$tmdbAPI/discover/tv?api_key=$apiKey&with_networks=2739" to "Disney+",
        "$tmdbAPI/discover/tv?api_key=$apiKey&with_networks=453" to "Hulu",
        "$tmdbAPI/discover/tv?api_key=$apiKey&with_networks=2552" to "Apple TV+",
        "$tmdbAPI/discover/tv?api_key=$apiKey&with_networks=49" to "HBO",
        "$tmdbAPI/discover/tv?api_key=$apiKey&with_networks=4330" to "Paramount+",
        "$tmdbAPI/movie/top_rated?api_key=$apiKey&region=US" to "Top Rated Movies",
        "$tmdbAPI/tv/top_rated?api_key=$apiKey&region=US" to "Top Rated TV Shows",
        "$tmdbAPI/movie/upcoming?api_key=$apiKey&region=US" to "Upcoming Movies",
        "$tmdbAPI/discover/tv?api_key=$apiKey&with_original_language=ko" to "Korean Shows",
        "$tmdbAPI/tv/airing_today?api_key=$apiKey&with_keywords=210024|222243&sort_by=primary_release_date.desc" to "Airing Today Anime",
        "$tmdbAPI/tv/on_the_air?api_key=$apiKey&with_keywords=210024|222243&sort_by=primary_release_date.desc" to "Ongoing Anime",
        "$tmdbAPI/discover/tv?api_key=$apiKey&with_keywords=210024|222243" to "Anime",
        "$tmdbAPI/discover/movie?api_key=$apiKey&with_keywords=210024|222243" to "Anime Movies",
    )

    private fun getImageUrl(link: String?): String? {
        if (link == null) return null
        return if (link.startsWith("/")) "https://image.tmdb.org/t/p/w500/$link" else link
    }

    private fun getOriImageUrl(link: String?): String? {
        if (link == null) return null
        return if (link.startsWith("/")) "https://image.tmdb.org/t/p/original/$link" else link
    }

    override suspend fun getMainPage(
        page: Int,
        request: MainPageRequest
    ): HomePageResponse {
        val adultQuery =
            if (settingsForProvider.enableAdult) "" else "&without_keywords=190370|13059|226161|195669"
        val type = if (request.data.contains("/movie")) "movie" else "tv"
        val home = app.get("${request.data}$adultQuery&page=$page")
            .parsedSafe<Results>()?.results
            ?.mapNotNull { media ->
                media.toSearchResponse(type)
            } ?: throw ErrorLoadingException("Invalid Json reponse")
        return newHomePageResponse(request.name, home)
    }

    private fun Media.toSearchResponse(type: String? = null): SearchResponse? {
        return newMovieSearchResponse(
            title ?: name ?: originalTitle ?: return null,
            Data(id = id, type = mediaType ?: type).toJson(),
            TvType.Movie,
        ) {
            this.posterUrl = getImageUrl(posterPath)
        }
    }

    override suspend fun quickSearch(query: String): List<SearchResponse>? = search(query)

    override suspend fun search(query: String): List<SearchResponse>? {
        return app.get(
            "$tmdbAPI/search/multi?api_key=$apiKey&language=en-US&query=$query&page=1&include_adult=${settingsForProvider.enableAdult}"
        ).parsedSafe<Results>()?.results?.mapNotNull { media ->
            media.toSearchResponse()
        }
    }

    override suspend fun load(url: String): LoadResponse? {
        val data = parseJson<Data>(url)
        val type = getType(data.type)
        val append = "alternative_titles,credits,external_ids,keywords,videos,recommendations"
        val resUrl = if (type == TvType.Movie) {
            "$tmdbAPI/movie/${data.id}?api_key=$apiKey&append_to_response=$append"
        } else {
            "$tmdbAPI/tv/${data.id}?api_key=$apiKey&append_to_response=$append"
        }
        val res = app.get(resUrl).parsedSafe<MediaDetail>()
            ?: throw ErrorLoadingException("Invalid Json Response")

        val title = res.title ?: res.name ?: return null
        val poster = getOriImageUrl(res.posterPath)
        val bgPoster = getOriImageUrl(res.backdropPath)
        val orgTitle = res.originalTitle ?: res.originalName ?: return null
        val releaseDate = res.releaseDate ?: res.firstAirDate
        val year = releaseDate?.split("-")?.first()?.toIntOrNull()
        val rating = res.vote_average.toString().toRatingInt()
        val genres = res.genres?.mapNotNull { it.name }
        val isAnime = genres?.contains("Animation") == true && (res.original_language == "zh" || res.original_language == "ja")
        val keywords = res.keywords?.results?.mapNotNull { it.name }.orEmpty()
            .ifEmpty { res.keywords?.keywords?.mapNotNull { it.name } }

        val actors = res.credits?.cast?.mapNotNull { cast ->
            ActorData(
                Actor(
                    cast.name ?: cast.originalName ?: return@mapNotNull null,
                    getImageUrl(cast.profilePath)
                ),
                roleString = cast.character
            )
        } ?: return null
        val recommendations =
            res.recommendations?.results?.mapNotNull { media -> media.toSearchResponse() }

        val trailer = res.videos?.results?.map { "https://www.youtube.com/watch?v=${it.key}" }
            ?.randomOrNull()

        return if (type == TvType.TvSeries) {
            val lastSeason = res.last_episode_to_air?.season_number
            val episodes = res.seasons?.mapNotNull { season ->
                app.get("$tmdbAPI/${data.type}/${data.id}/season/${season.seasonNumber}?api_key=$apiKey")
                    .parsedSafe<MediaDetailEpisodes>()?.episodes?.map { eps ->
                        Episode(
                            LinkData(
                                data.id,
                                res.external_ids?.imdb_id,
                                data.type,
                                eps.seasonNumber,
                                eps.episodeNumber,
                                title = title,
                                year = season.airDate?.split("-")?.first()?.toIntOrNull(),
                                orgTitle = orgTitle,
                                isAnime = isAnime,
                                airedYear = year,
                                lastSeason = lastSeason,
                                epsTitle = eps.name,
                                jpTitle = res.alternative_titles?.results?.find { it.iso_3166_1 == "JP" }?.title,
                                date = season.airDate,
                                airedDate = res.releaseDate ?: res.firstAirDate,
                            ).toJson(),
                            name = eps.name + if(isUpcoming(eps.airDate)) " - [UPCOMING]" else "",
                            season = eps.seasonNumber,
                            episode = eps.episodeNumber,
                            posterUrl = getImageUrl(eps.stillPath),
                            rating = eps.voteAverage?.times(10)?.roundToInt(),
                            description = eps.overview
                        ).apply {
                            this.addDate(eps.airDate)
                        }
                    }
            }?.flatten() ?: listOf()
            newTvSeriesLoadResponse(
                title,
                url,
                if (isAnime) TvType.Anime else TvType.TvSeries,
                episodes
            ) {
                this.posterUrl = poster
                this.backgroundPosterUrl = bgPoster
                this.year = year
                this.plot = res.overview
                this.tags = if (isAnime) keywords else genres
                this.rating = rating
                this.showStatus = getStatus(res.status)
                this.recommendations = recommendations
                this.actors = actors
                addTrailer(trailer)
            }
        } else {
            newMovieLoadResponse(
                title,
                url,
                TvType.Movie,
                LinkData(
                    data.id,
                    res.external_ids?.imdb_id,
                    data.type,
                    title = title,
                    year = year,
                    orgTitle = orgTitle,
                    isAnime = isAnime,
                    jpTitle = res.alternative_titles?.results?.find { it.iso_3166_1 == "JP" }?.title,
                    airedDate = res.releaseDate ?: res.firstAirDate,
                ).toJson(),
            ) {
                this.posterUrl = poster
                this.backgroundPosterUrl = bgPoster
                this.comingSoon = isUpcoming(releaseDate)
                this.year = year
                this.plot = res.overview
                this.duration = res.runtime
                this.tags = if (isAnime) keywords else genres
                this.rating = rating
                this.recommendations = recommendations
                this.actors = actors
                addTrailer(trailer)
            }
        }
    }

    override suspend fun extractorVerifierJob(extractorData: String?) {
        if (extractorData == null) return
        VidSrcExtractor.validatePass(extractorData)
    }

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {

        val res = parseJson<LinkData>(data)

        argamap(
            {
                
            }
        )

        return true
    }

    data class LinkData(
        val id: Int? = null,
        val imdbId: String? = null,
        val type: String? = null,
        val season: Int? = null,
        val episode: Int? = null,
        val aniId: String? = null,
        val animeId: String? = null,
        val title: String? = null,
        val year: Int? = null,
        val orgTitle: String? = null,
        val isAnime: Boolean = false,
        val airedYear: Int? = null,
        val lastSeason: Int? = null,
        val epsTitle: String? = null,
        val jpTitle: String? = null,
        val date: String? = null,
        val airedDate: String? = null,
    )

    data class Data(
        val id: Int? = null,
        val type: String? = null,
        val aniId: String? = null,
        val malId: Int? = null,
    )

    data class Results(
        @JsonProperty("results") val results: ArrayList<Media>? = arrayListOf(),
    )

    data class Media(
        @JsonProperty("id") val id: Int? = null,
        @JsonProperty("name") val name: String? = null,
        @JsonProperty("title") val title: String? = null,
        @JsonProperty("original_title") val originalTitle: String? = null,
        @JsonProperty("media_type") val mediaType: String? = null,
        @JsonProperty("poster_path") val posterPath: String? = null,
    )

    data class Genres(
        @JsonProperty("id") val id: Int? = null,
        @JsonProperty("name") val name: String? = null,
    )

    data class Keywords(
        @JsonProperty("id") val id: Int? = null,
        @JsonProperty("name") val name: String? = null,
    )

    data class KeywordResults(
        @JsonProperty("results") val results: ArrayList<Keywords>? = arrayListOf(),
        @JsonProperty("keywords") val keywords: ArrayList<Keywords>? = arrayListOf(),
    )

    data class Seasons(
        @JsonProperty("id") val id: Int? = null,
        @JsonProperty("name") val name: String? = null,
        @JsonProperty("season_number") val seasonNumber: Int? = null,
        @JsonProperty("air_date") val airDate: String? = null,
    )

    data class Cast(
        @JsonProperty("id") val id: Int? = null,
        @JsonProperty("name") val name: String? = null,
        @JsonProperty("original_name") val originalName: String? = null,
        @JsonProperty("character") val character: String? = null,
        @JsonProperty("known_for_department") val knownForDepartment: String? = null,
        @JsonProperty("profile_path") val profilePath: String? = null,
    )

    data class Episodes(
        @JsonProperty("id") val id: Int? = null,
        @JsonProperty("name") val name: String? = null,
        @JsonProperty("overview") val overview: String? = null,
        @JsonProperty("air_date") val airDate: String? = null,
        @JsonProperty("still_path") val stillPath: String? = null,
        @JsonProperty("vote_average") val voteAverage: Double? = null,
        @JsonProperty("episode_number") val episodeNumber: Int? = null,
        @JsonProperty("season_number") val seasonNumber: Int? = null,
    )

    data class MediaDetailEpisodes(
        @JsonProperty("episodes") val episodes: ArrayList<Episodes>? = arrayListOf(),
    )

    data class Trailers(
        @JsonProperty("key") val key: String? = null,
    )

    data class ResultsTrailer(
        @JsonProperty("results") val results: ArrayList<Trailers>? = arrayListOf(),
    )

    data class AltTitles(
        @JsonProperty("iso_3166_1") val iso_3166_1: String? = null,
        @JsonProperty("title") val title: String? = null,
        @JsonProperty("type") val type: String? = null,
    )

    data class ResultsAltTitles(
        @JsonProperty("results") val results: ArrayList<AltTitles>? = arrayListOf(),
    )

    data class ExternalIds(
        @JsonProperty("imdb_id") val imdb_id: String? = null,
        @JsonProperty("tvdb_id") val tvdb_id: String? = null,
    )

    data class Credits(
        @JsonProperty("cast") val cast: ArrayList<Cast>? = arrayListOf(),
    )

    data class ResultsRecommendations(
        @JsonProperty("results") val results: ArrayList<Media>? = arrayListOf(),
    )

    data class LastEpisodeToAir(
        @JsonProperty("episode_number") val episode_number: Int? = null,
        @JsonProperty("season_number") val season_number: Int? = null,
    )

    data class MediaDetail(
        @JsonProperty("id") val id: Int? = null,
        @JsonProperty("imdb_id") val imdbId: String? = null,
        @JsonProperty("title") val title: String? = null,
        @JsonProperty("name") val name: String? = null,
        @JsonProperty("original_title") val originalTitle: String? = null,
        @JsonProperty("original_name") val originalName: String? = null,
        @JsonProperty("poster_path") val posterPath: String? = null,
        @JsonProperty("backdrop_path") val backdropPath: String? = null,
        @JsonProperty("release_date") val releaseDate: String? = null,
        @JsonProperty("first_air_date") val firstAirDate: String? = null,
        @JsonProperty("overview") val overview: String? = null,
        @JsonProperty("runtime") val runtime: Int? = null,
        @JsonProperty("vote_average") val vote_average: Any? = null,
        @JsonProperty("original_language") val original_language: String? = null,
        @JsonProperty("status") val status: String? = null,
        @JsonProperty("genres") val genres: ArrayList<Genres>? = arrayListOf(),
        @JsonProperty("keywords") val keywords: KeywordResults? = null,
        @JsonProperty("last_episode_to_air") val last_episode_to_air: LastEpisodeToAir? = null,
        @JsonProperty("seasons") val seasons: ArrayList<Seasons>? = arrayListOf(),
        @JsonProperty("videos") val videos: ResultsTrailer? = null,
        @JsonProperty("external_ids") val external_ids: ExternalIds? = null,
        @JsonProperty("credits") val credits: Credits? = null,
        @JsonProperty("recommendations") val recommendations: ResultsRecommendations? = null,
        @JsonProperty("alternative_titles") val alternative_titles: ResultsAltTitles? = null,
    )

    data class EmbedJson(
        @JsonProperty("type") val type: String? = null,
        @JsonProperty("link") val link: String? = null,
        @JsonProperty("sources") val sources: List<String?> = arrayListOf(),
        @JsonProperty("tracks") val tracks: List<String>? = null,
    )

    data class MovieHabData(
        @JsonProperty("link") val link: String? = null,
        @JsonProperty("token") val token: String? = null,
    )

    data class MovieHabRes(
        @JsonProperty("data") val data: MovieHabData? = null,
    )

}
