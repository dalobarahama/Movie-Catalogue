package com.example.moviecataloguejetpackpro.ui.detail

import com.example.moviecataloguejetpackpro.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyEntity = DataDummy.generateDummyMovies()[3]
    private val entityType = dummyEntity.type
    private val entityId = dummyEntity.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedItem(entityType, entityId)
    }

    @Test
    fun getEntity() {
        viewModel.setSelectedItem(entityType, entityId)
        val entity = viewModel.getItem()
        assertNotNull(entity)
        assertEquals(dummyEntity.title, entity.title)
        assertEquals(dummyEntity.overview, entity.overview)
        assertEquals(dummyEntity.releaseDate, entity.releaseDate)
        assertEquals(dummyEntity.score, entity.score)
        assertEquals(dummyEntity.tags, entity.tags)
        assertEquals(dummyEntity.imagePath, entity.imagePath)
    }
}