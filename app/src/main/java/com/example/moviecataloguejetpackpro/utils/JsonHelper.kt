package com.example.moviecataloguejetpackpro.utils

import android.content.Context
import com.example.moviecataloguejetpackpro.data.remote.response.EntityResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadEntity(): List<EntityResponse> {
        val list = ArrayList<EntityResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("CourseResponse.json").toString())
            val listArray = responseObject.getJSONArray("courses")
            for (i in 0 until listArray.length()) {
                val entity = listArray.getJSONObject(i)

                val id = entity.getString("id")
                val type = entity.getString("type")
                val title = entity.getString("title")
                val overview = entity.getString("overview")
                val tags = entity.getString("tags")
                val score = entity.getString("score")
                val releaseDate = entity.getString("releaseDate")
                val imagePath = entity.getString("imagePath")

                val entityResponse = EntityResponse(
                    type,
                    id,
                    title,
                    overview,
                    tags,
                    score,
                    releaseDate,
                    Integer.valueOf(imagePath)
                )
                list.add(entityResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }
}