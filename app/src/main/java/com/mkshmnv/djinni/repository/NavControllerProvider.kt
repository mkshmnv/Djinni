package com.mkshmnv.djinni.repository

import androidx.navigation.NavController

interface NavControllerProvider {
    fun getNavController(): NavController
}