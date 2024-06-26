package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurveService;

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
public class CurveController {
	private static final Logger logger = LoggerFactory.getLogger(CurveController.class);

	@Autowired
	private CurveService curvePointService;

	@RequestMapping("/curvePoint/list")
	public String home(Model model){
		try {
			return curvePointService.home(model);
		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}


	/**
	 * Get all curvePoint
	 * @return "curvePoint/add"
	 */
	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		return "curvePoint/add";
	}

	/**
	 * Post for validate curvePoint 
	 * @RequestBody CurvePoint : curvePoint
	 * @return "redirect:/curvePoint/list"
	 */
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		try {
			return curvePointService.validate(curvePoint, result, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}

	/**
	 * Update one curvePoint by id
	 * @Param id : id
	 * @return "curvePoint/update"
	 */
	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		try {
			return curvePointService.showUpdateForm(id, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}

	/**
	 * Update one curvePoint by id
	 * @Param id : id
	 * @return "redirect:/curvePoint/list"
	 */
	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,BindingResult result, Model model) {
		try {
			return curvePointService.updateBid(id, curvePoint, result, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}

	/**
	 * Delete one curvePoint by id
	 * @Param id : id
	 * @return "redirect:/curvePoint/list"
	 */
	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		try {
			return curvePointService.deleteBid(id, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}
}
