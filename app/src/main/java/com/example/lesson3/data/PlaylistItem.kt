package com.example.lesson3.data

import java.io.Serializable

data class PlaylistItem(
    val etag: String,
    val items: List<Item?>,
    val kind: String,
    val pageInfo: PageInfo
) : Serializable {
    data class Item(
        val contentDetails: ContentDetails,
        val etag: String,
        val id: String,
        val kind: String,
        val snippet: Snippet
    ) : Serializable {
        data class ContentDetails(
            val videoId: String,
            val videoPublishedAt: String,
            val videoDuration: String
        ) : Serializable

        data class Snippet(
            val channelId: String,
            val channelTitle: String,
            val description: String,
            val playlistId: String,
            val position: Int,
            val publishedAt: String,
            val resourceId: ResourceId,
            val thumbnails: Thumbnails,
            val title: String,
            val videoOwnerChannelId: String,
            val videoOwnerChannelTitle: String
        ) : Serializable {
            data class ResourceId(
                val kind: String,
                val videoId: String
            ) : Serializable

            data class Thumbnails(
                val default: Default,
                val high: High,
                val medium: Medium,
                val standard: Standard
            ) : Serializable {
                data class Default(
                    val height: Int,
                    val url: String,
                    val width: Int
                ) : Serializable

                data class High(
                    val height: Int?,
                    val url: String?,
                    val width: Int?
                ) : Serializable

                data class Medium(
                    val height: Int?,
                    val url: String?,
                    val width: Int?
                ) : Serializable

                data class Standard(
                    val height: Int?,
                    val url: String?,
                    val width: Int?
                ) : Serializable
            }
        }
    }

    data class PageInfo(
        val resultsPerPage: Int?,
        val totalResults: Int?
    ) : Serializable
}