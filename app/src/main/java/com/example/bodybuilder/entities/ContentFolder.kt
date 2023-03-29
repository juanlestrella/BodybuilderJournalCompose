package com.example.bodybuilder.entities

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "contentFolder")
data class ContentFolderData (
        val contentCardData: ContentCardData,
) :Parcelable