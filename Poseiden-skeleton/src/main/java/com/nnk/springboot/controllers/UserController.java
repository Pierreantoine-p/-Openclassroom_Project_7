package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * Get all users
	 * @return "user/list"
	 */
	@RequestMapping("/user/list")
	public String home(Model model)
	{
		try {
			 		return userService.home(model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}

	/**
	 * add users
	 * @return "user/add"
	 */
	@GetMapping("/user/add")
	public String addUser(User user) {
		return "user/add";
	}

	/**
	 * Post for validate user connexion
	 * @RequestBody User : user
	 * @return "redirect:/user/list"
	 */
	@PostMapping("/user/validate")
	public String validate(@Valid User user, BindingResult result, Model model) {
		try {
					return userService.validate(user, result, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}

	/**
	 * Update one user by id
	 * @Param id : id
	 * @return "user/update"
	 */
	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		try {
					return userService.showUpdateForm(id, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}

	/**
	 * Update one user by id
	 * @Param id : id
	 * @return "redirect:/user/list"
	 */
	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid User user,
			BindingResult result, Model model) {
		try {
					return userService.updateUser(id, user, result, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}

	/**
	 * Delete one user by id
	 * @Param id : id
	 * @return "redirect:/user/list"
	 */
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		try {
					return userService.deleteUser(id, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}

}
