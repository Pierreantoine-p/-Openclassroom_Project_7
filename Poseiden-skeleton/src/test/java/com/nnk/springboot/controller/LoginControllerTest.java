package com.nnk.springboot.controller;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.nnk.springboot.controllers.LoginController;
import com.nnk.springboot.service.LoginService;
import org.springframework.web.servlet.ModelAndView;
import static org.junit.jupiter.api.Assertions.assertEquals;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class LoginControllerTest {

	@Autowired
	private LoginController loginController;

	@Autowired
	private LoginService loginService;

	@Test
	@Order(1)
	public void testLogin() {

		ModelAndView result = loginController.login();

		assertEquals("/bidList/list",result.getViewName());
	}

	@Test
	@Order(2)
	public void testGetAllUserArticles() {
		ModelAndView result = loginController.getAllUserArticles();

		assertEquals("user/list", result.getViewName());

	}

	@Test
	@Order(2)
	public void testError() {
		ModelAndView result = loginController.error();

		assertEquals("403", result.getViewName());

	}
}
