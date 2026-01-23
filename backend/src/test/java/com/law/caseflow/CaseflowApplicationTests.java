package com.law.caseflow;

import com.law.caseflow.service.producer.CaseProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CaseflowApplicationTests {

	// RabbitMQ bağlantısı kurmaya çalışmasın diye Producer'ı mockluyoruz.
	// Böylece gerçek bir RabbitMQ sunucusuna ihtiyaç duymadan context ayağa kalkabilir.
	@MockBean
	private CaseProducer caseProducer;

	@Test
	void contextLoads() {
		// Uygulama context'inin (Spring Boot) hatasız ayağa kalkıp kalkmadığını test eder.
	}

}
