package com.example.newsfeedr.domain.usecases.app_entry

import com.example.newsfeedr.domain.manager.LocalUserManger

class SaveAppEntry(
    private val localUserManager: LocalUserManger
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}