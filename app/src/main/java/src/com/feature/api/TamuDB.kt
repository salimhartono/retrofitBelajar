package src.com.feature.api

import src.com.feature.BuildConfig

object TamuDB {
    fun getTeamById(teamByID: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookup_all_players.php?id=" + teamByID
    }

    fun getPlayersByIDTeam(teamID: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookup_all_players.php?id=" + teamID
    }

    fun getDAftarTamu(): String{
        return BuildConfig.BASE_TAMU+"tamu_list.php"
    }
}