package codes.zaak.kotlinmvvm.repository.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "movies")
data class Movie(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String = UUID.randomUUID().toString(),
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "year")
        val year: Int,
        @ColumnInfo(name = "rating")
        val rating: Int
)