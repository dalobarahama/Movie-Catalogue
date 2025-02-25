package com.example.moviecataloguejetpackpro.ui.common.basemvc

abstract class BaseViewMvcObservable<Listener> : ViewMvcObservable<Listener>, BaseViewMvc() {

    private val listeners = HashSet<Listener>()

    override fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    fun getListener(): Listener? {
        var listener: Listener? = null
        for (listener1 in listeners) {
            listener = listener1
        }
        return listener
    }
}