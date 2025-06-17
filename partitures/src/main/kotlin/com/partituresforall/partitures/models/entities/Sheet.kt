package com.partituresforall.partitures.models.entities

import jakarta.persistence.*

@Entity
@Table(name = "sheets")
data class Sheet(
    @Column(nullable = false, length = 150)
    var title: String,

    @Column(length = 500)
    var description: String? = null,

    @Column(nullable = false, length = 500)
    var pdfReference: String,

    @Column(nullable = false)
    var isPublic: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    var owner: User
) : BaseEntity()