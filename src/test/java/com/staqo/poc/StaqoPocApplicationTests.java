package com.staqo.poc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.staqo.poc.controller.WebController;

@SpringBootTest
@WebMvcTest(value = WebController.class)
class StaqoPocApplicationTests {

	@Test
	void contextLoads() {
	}

}
