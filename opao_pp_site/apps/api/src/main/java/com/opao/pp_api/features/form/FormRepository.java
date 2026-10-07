package com.opao.pp_api.features.form;

import org.springframework.data.domain.Page; // 💡 Added for pagination
import org.springframework.data.domain.Pageable; // 💡 Added for pagination
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opao.pp_api.features.form.model.FormEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormRepository extends JpaRepository<FormEntity, Integer> {

    // Note: Standard Page<FormEntity> findAll(Pageable pageable) is already provided by JpaRepository

    Optional<FormEntity> findByFormId(Integer formId);

    // ─────────────────────────────────────────────────────────────────────────
    // 🟢 KEEP YOUR ORIGINAL LIST FILTER ENDPOINTS
    // ─────────────────────────────────────────────────────────────────────────
    List<FormEntity> findByTitle(String title);
    List<FormEntity> findByFilingYear(Integer filingYear);
    List<FormEntity> findByBillNumber(String billNumber);
    List<FormEntity> findByBillNumberAndPin(String billNumber, String pin);
    List<FormEntity> findByBillNumberAndFilingYear(String billNumber, Integer filingYear);
    List<FormEntity> findByFilingYearAndBillNumberAndPin(Integer filingYear, String billNumber, String pin);
    List<FormEntity> findByBillNumberAndPinAndStatusName(String billNumber, String pin, String statusName);

    // ─────────────────────────────────────────────────────────────────────────
    // ⚡ ADD OVERLOADED PAGEABLE OPTIONS BELOW
    // ─────────────────────────────────────────────────────────────────────────
    Page<FormEntity> findByTitle(String title, Pageable pageable);
    Page<FormEntity> findByFilingYear(Integer filingYear, Pageable pageable);
    Page<FormEntity> findByBillNumber(String billNumber, Pageable pageable);
    Page<FormEntity> findByBillNumberAndPin(String billNumber, String pin, Pageable pageable);
    Page<FormEntity> findByBillNumberAndFilingYear(String billNumber, Integer filingYear, Pageable pageable);
    Page<FormEntity> findByFilingYearAndBillNumberAndPin(Integer filingYear, String billNumber, String pin, Pageable pageable);
    Page<FormEntity> findByBillNumberAndPinAndStatusName(String billNumber, String pin, String statusName, Pageable pageable);
}
