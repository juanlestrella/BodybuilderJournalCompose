package com.example.bodybuilder.entities

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "content folder")
data class ContentFolderData (
        val contentCardData: ContentCardData,
) :Parcelable