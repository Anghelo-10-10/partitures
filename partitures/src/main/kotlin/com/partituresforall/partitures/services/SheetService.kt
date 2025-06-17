package com.partituresforall.partitures.services

import com.partituresforall.partitures.models.entities.Sheet
import com.partituresforall.partitures.models.entities.User
import com.partituresforall.partitures.models.requests.CreateSheetRequest
import com.partituresforall.partitures.models.requests.UpdateSheetRequest
import com.partituresforall.partitures.models.responses.SheetResponse
import com.partituresforall.partitures.repositories.SheetRepository
import com.partituresforall.partitures.repositories.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class SheetService(
    private val sheetRepository: SheetRepository,
    private val userRepository: UserRepository
) {
    fun createSheet(request: CreateSheetRequest): SheetResponse {
        val owner = userRepository.findById(request.ownerId).get()
        val sheet = sheetRepository.save(
            Sheet(
                title = request.title,
                description = request.description,
                pdfReference = request.pdfReference,
                isPublic = request.isPublic,
                owner = owner
            )
        )
        return sheet.toResponse()
    }

    fun getSheetById(id: Long): SheetResponse {
        return sheetRepository.findById(id).get().toResponse()
    }

    fun updateSheet(id: Long, request: UpdateSheetRequest): SheetResponse {
        val sheet = sheetRepository.findById(id).get()
        request.title?.let { sheet.title = it }
        request.description?.let { sheet.description = it }
        request.isPublic?.let { sheet.isPublic = it }
        return sheetRepository.save(sheet).toResponse()
    }

    fun deleteSheet(id: Long) = sheetRepository.deleteById(id)

    private fun Sheet.toResponse() = SheetResponse(
        id = this.id,
        title = this.title,
        description = this.description,
        pdfReference = this.pdfReference,
        isPublic = this.isPublic,
        ownerId = this.owner.id,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}