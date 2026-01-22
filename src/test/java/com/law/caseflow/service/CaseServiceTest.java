package com.law.caseflow.service;

import com.law.caseflow.domain.entity.CaseFile;
import com.law.caseflow.domain.entity.Client;
import com.law.caseflow.domain.enums.CaseStatus;
import com.law.caseflow.dto.CaseCreateRequest;
import com.law.caseflow.dto.CaseResponse;
import com.law.caseflow.event.CaseCreatedEvent;
import com.law.caseflow.exception.NotFoundException;
import com.law.caseflow.repository.CaseRepository;
import com.law.caseflow.service.producer.CaseProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CaseServiceTest {

    @Mock
    private CaseRepository caseRepository;

    @Mock
    private CaseProducer caseProducer; // EKLENDİ: RabbitMQ Producer Mock

    @InjectMocks
    private CaseService caseService;

    private CaseFile sampleCase;
    private Client sampleClient;

    @BeforeEach
    void setUp() {
        sampleClient = new Client();
        sampleClient.setEmail("client@test.com");
        sampleClient.setFirstName("Ali");
        sampleClient.setLastName("Veli");

        sampleCase = new CaseFile();
        sampleCase.setCaseNumber("2023/101");
        sampleCase.setTitle("Test Davası");
        sampleCase.setDescription("Test Açıklaması");
        sampleCase.setStatus(CaseStatus.OPEN);
        sampleCase.setClient(sampleClient);
    }

    @Test
    void create_ShouldSaveCaseAndSendEvent() {
        // Arrange
        CaseCreateRequest request = new CaseCreateRequest("2023/101", "Test Davası", "Açıklama");
        when(caseRepository.save(any(CaseFile.class))).thenReturn(sampleCase);

        // Act
        CaseResponse response = caseService.create(request, sampleClient);

        // Assert
        assertNotNull(response);
        assertEquals("2023/101", response.caseNumber());

        // 1. Repository save çağrıldı mı?
        verify(caseRepository, times(1)).save(any(CaseFile.class));

        // 2. RabbitMQ Producer çağrıldı mı? (Event fırlatıldı mı?)
        verify(caseProducer, times(1)).sendCaseCreatedEvent(any(CaseCreatedEvent.class));
    }

    @Test
    void getAllCases_ShouldReturnListOfCaseResponses() {
        when(caseRepository.findAll()).thenReturn(List.of(sampleCase));

        List<CaseResponse> result = caseService.getAllCases();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(caseRepository, times(1)).findAll();
    }

    @Test
    void getEntityByCaseNumber_WhenCaseExists_ShouldReturnCaseFile() {
        when(caseRepository.findByCaseNumber("2023/101")).thenReturn(sampleCase);

        CaseFile result = caseService.getEntityByCaseNumber("2023/101");

        assertNotNull(result);
        assertEquals("2023/101", result.getCaseNumber());
    }

    @Test
    void getEntityByCaseNumber_WhenCaseDoesNotExist_ShouldThrowNotFoundException() {
        when(caseRepository.findByCaseNumber("9999/99")).thenReturn(null);

        assertThrows(NotFoundException.class, () -> {
            caseService.getEntityByCaseNumber("9999/99");
        });
    }
}
