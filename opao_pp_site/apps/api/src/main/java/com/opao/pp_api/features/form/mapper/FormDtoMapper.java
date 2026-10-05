package com.opao.pp_api.features.form.mapper;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

import com.opao.pp_api.features.form.dto.request.FormCreateRequest;
import com.opao.pp_api.features.form.dto.request.FormUpdateRequest;
import com.opao.pp_api.features.form.dto.response.FormResponse;
import com.opao.pp_api.features.form.model.Form;

@Component
public class FormDtoMapper {

    /**
     * Maps an incoming FormCreateRequest into a fresh business Domain Model.
     * Automatically handles internal initialization defaults like setting up modification 
     * tracking metadata and line item presence statuses.
     */
    public Form toDomain(FormCreateRequest request) {
        if (request == null) return null;

        return Form.builder()
                .title(request.getTitle())
                .filingYear(request.getFilingYear() != null ? request.getFilingYear() : 0)
                .billNumber(request.getBillNumber())
                .pin(request.getPin())
                .formTypeId(request.getFormTypeId())
                .userId(request.getUserId())
                .lastModifiedDate(LocalDateTime.now()) // 💡 Auto-initialize management timestamp
                .statusName("DRAFT") // 💡 Default logical baseline state matching standard lifecycle
                .hasLineItems(false) // 💡 Default fallback status value for empty forms
                .build();
    }

    /**
     * Maps a path-bound ID variable alongside an incoming FormUpdateRequest payload.
     * Keeps unassigned wrappers as null values so your business domain patch service 
     * layer can bypass updating parameters left out of the request payload.
     */
    public Form toDomain(Integer id, FormUpdateRequest request) {
        if (request == null) return null;

        return Form.builder()
                .id(id)
                .title(request.getTitle())
                .filingYear(request.getFilingYear()) // 💡 Allowed to carry null straight to patch validation
                .billNumber(request.getBillNumber())
                .pin(request.getPin())
                .formTypeId(request.getFormTypeId())
                .build();
    }

    /**
     * Translates a populated internal business Domain Model instance back out to a client-safe Response Record.
     */
    public FormResponse toResponse(Form domain) {
        if (domain == null) return null;

        return new FormResponse(
                domain.getId(),
                domain.getTitle(),
                domain.getFilingYear(),
                domain.getLastModifiedDate(),
                domain.getBillNumber(),
                domain.getPin(),
                domain.getFormTypeId(),
                domain.getStatusName(),
                domain.getUserId(),
                domain.isHasLineItems()
        );
    }
}
