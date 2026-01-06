package com.law.caseflow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		properties = {
				"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"
		}
)
class CaseflowApplicationTests {
}


/*@SpringBootTest
class CaseflowApplicationTests {

	@Test
	void contextLoads() {
	}

}*/
