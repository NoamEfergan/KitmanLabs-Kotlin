import kotlinx.serialization.Serializable

@Serializable
data class SquadItem(
    val created_at: String,
    val id: Int,
    val name: String,
    val organisation_id: Int,
    val updated_at: String
) {
    companion object {
        val mock = listOf(
            SquadItem(
                created_at = "2015-09-14T18:26:11.000Z",
                id = 78,
                name = "staff",
                organisation_id = 6,
                updated_at = "2015-09-14T18:26:11.000Z"
            ),
            SquadItem(
                created_at = "2015-09-02T19:42:22.000Z",
                id = 72,
                name = "Active Roster",
                organisation_id = 6,
                updated_at = "2015-09-02T19:42:22.000Z"
            ),
            SquadItem(
                created_at = "2016-03-17T21:55:10.000Z",
                id = 185,
                name = "Offense",
                organisation_id = 6,
                updated_at = "2016-03-17T21:55:10.000Z"
            ),
            SquadItem(
                created_at = "2016-03-17T22:08:39.000Z",
                id = 190,
                name = "OL",
                organisation_id = 6,
                updated_at = "2016-03-29T18:41:15.000Z"
            ),
            SquadItem(
                created_at = "2016-03-17T22:08:06.000Z",
                id = 189,
                name = "WR",
                organisation_id = 6,
                updated_at = "2016-03-29T18:40:15.000Z"
            ),

            SquadItem(
                created_at = "2016-03-17T22:09:20.000Z",
                id = 191,
                name = "SPEC",
                organisation_id = 6,
                updated_at = "2016-03-29T18:41:51.000Z"
            ),
            SquadItem(
                created_at = "2016-03-17T21:55:56.000Z",
                id = 186,
                name = "Defense",
                organisation_id = 6,
                updated_at = "2016-03-17T21:55:56.000Z"
            ),

            )
    }
}
