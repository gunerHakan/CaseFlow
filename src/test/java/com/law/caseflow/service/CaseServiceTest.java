package com.law.caseflow.service;

import com.law.caseflow.domain.entity.CaseFile;
import com.law.caseflow.domain.enums.CaseStatus;
import com.law.caseflow.dto.CaseResponse;
import com.law.caseflow.exception.NotFoundException;
import com.law.caseflow.repository.CaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CaseServiceTest {

    @Mock
    private CaseRepository caseRepository;

    @InjectMocks
    private CaseService caseService;

    private CaseFile sampleCase;

    @BeforeEach
    void setUp() {
        // Her testten önce çalışır ve temiz bir veri hazırlar
        sampleCase = new CaseFile();
        sampleCase.setCaseNumber("2023/101");
        sampleCase.setTitle("Test Davası");
        sampleCase.setDescription("Test Açıklaması");
        sampleCase.setStatus(CaseStatus.OPEN);
    }

    @Test
    void getAllCases_ShouldReturnListOfCaseResponses() {
        // Arrange (Hazırlık)
        when(caseRepository.findAll()).thenReturn(List.of(sampleCase));

        // Act (Eylem)
        List<CaseResponse> result = caseService.getAllCases();

        // Assert (Kontrol)
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("2023/101", result.get(0).caseNumber());
        
        // Repository'nin findAll metodunun tam olarak 1 kere çağrıldığını doğrula
        verify(caseRepository, times(1)).findAll();
    }

    @Test
    void getEntityByCaseNumber_WhenCaseExists_ShouldReturnCaseFile() {
        // Arrange
        when(caseRepository.findByCaseNumber("2023/101")).thenReturn(sampleCase);

        // Act
        CaseFile result = caseService.getEntityByCaseNumber("2023/101");

        // Assert
        assertNotNull(result);
        assertEquals("2023/101", result.getCaseNumber());
    }

    @Test
    void getEntityByCaseNumber_WhenCaseDoesNotExist_ShouldThrowNotFoundException() {
        // Arrange
        when(caseRepository.findByCaseNumber("9999/99")).thenReturn(null);

        // Act & Assert
        // Olmayan bir numara ile çağrıldığında hata fırlatmalı
        assertThrows(NotFoundException.class, () -> {
            caseService.getEntityByCaseNumber("9999/99");
        });
    }
}
