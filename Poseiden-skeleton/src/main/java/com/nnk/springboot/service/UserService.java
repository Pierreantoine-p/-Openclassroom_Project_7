package com.nnk.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	
	/**
	 * Get all users
	 * @return "user/list"
	 */
	@RequestMapping("/user/list")
	public String home(Model model)
	{

		try {
			model.addAttribute("users", userRepository.findAll());
			return "user/list";	

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}


	/**
	 * Post for validate user connexion
	 * @RequestBody User : user
	 * @return "redirect:/user/list"
	 */
	@PostMapping("/user/validate")
	public String validate(@Valid User user, BindingResult result, Model model) {

		try {
			if (!result.hasErrors()) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				user.setPassword(encoder.encode(user.getPassword()));
				userRepository.save(user);
				model.addAttribute("users", userRepository.findAll());
				return "redirect:/user/list";
			}

			return "user/add";

		}catch( Exception  e) {
			logger.error("error :" + e);
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
			User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
			user.setPassword("");
			model.addAttribute("user", user);
			return "user/update";

		}catch( Exception  e) {
			logger.error("error :" + e);
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
			if (result.hasErrors()) {
				return "user/update";
			}
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			user.setId(id);
			userRepository.save(user);
			model.addAttribute("users", userRepository.findAll());
			return "redirect:/user/list";

		}catch( Exception  e) {
			logger.error("error :" + e);
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

			User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
			userRepository.delete(user);
			model.addAttribute("users", userRepository.findAll());
			return "redirect:/user/list";
		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}
}
