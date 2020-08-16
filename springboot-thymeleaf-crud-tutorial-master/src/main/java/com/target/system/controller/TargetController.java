package com.target.system.controller;

import javax.validation.Valid;

import com.target.system.entity.TargetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.target.system.repository.TargetRepository;

@Controller
@RequestMapping("/targets/")
public class TargetController {

	@Autowired
	private final TargetRepository targetRepository;

	@Autowired
	public TargetController(TargetRepository targetRepository) {
		this.targetRepository = targetRepository;
	}

	@GetMapping("signup")
	public String showSignUpForm(TargetModel targetModel) {
		return "add-target";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model) {
		model.addAttribute("targets", targetRepository.findAll());
		return "index";
	}

	@PostMapping("add")
	public String addTargets(@Valid TargetModel targetModel, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-target";
		}

		targetRepository.save(targetModel);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		TargetModel targetModel = targetRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("targets", targetModel);
		return "update-target";
	}

	@PostMapping("update/{id}")
	public String updateTarget(@PathVariable("id") long id, @Valid TargetModel targetModel, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			targetModel.setId(id);
			return "update-target";
		}

		targetRepository.save(targetModel);
		model.addAttribute("targets", targetRepository.findAll());
		return "index";
	}

	@GetMapping("delete/{id}")
	public String deleteTarget(@PathVariable("id") long id, Model model) {
		TargetModel targetModel = targetRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid target Id:" + id));
		targetRepository.delete(targetModel);
		model.addAttribute("targets", targetRepository.findAll());
		return "index";
	}
}
