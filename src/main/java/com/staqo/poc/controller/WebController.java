package com.staqo.poc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.staqo.poc.model.User;
import com.staqo.poc.repository.UserRepository;
import com.staqo.util.FileUploadUtil;

@Controller
public class WebController {

	@Autowired
	private UserRepository repo;

	@GetMapping(value = { "/index", "index.html" })
	public String index(Model model) {
		java.util.List<User> users = (List<User>) repo.findTop5ByOrderByIdDesc();
		model.addAttribute("users", users);

		List<User> userList = repo.getCountByMonth();

		List<Map<String, String>> chartDataList = new ArrayList<>();

		/*
		 for (User user : userList) {
		 HashMap<String, String> chartDataMap = new HashMap<String, String>();
		 chartDataMap.put("Letter", user.getFirstName()); chartDataMap.put("Freq",
		  user.getId().toString()); chartDataList.add(chartDataMap); }
		 */

		HashMap<String, String> chartDataMap = new HashMap<String, String>();
		chartDataMap.put("Letter", "A");
		chartDataMap.put("Freq", "20");
		chartDataList.add(chartDataMap);

		chartDataMap = new HashMap<String, String>();
		chartDataMap.put("Letter", "B");
		chartDataMap.put("Freq", "12");
		chartDataList.add(chartDataMap);

		chartDataMap = new HashMap<String, String>();
		chartDataMap.put("Letter", "C");
		chartDataMap.put("Freq", "47");
		chartDataList.add(chartDataMap);

		chartDataMap = new HashMap<String, String>();
		chartDataMap.put("Letter", "D");
		chartDataMap.put("Freq", "34");
		chartDataList.add(chartDataMap);

		Gson gson = new Gson();
		String output = gson.toJson(chartDataList);

		model.addAttribute("chartData", output);
		
		model.addAttribute("user", new User());

		return "index";
	}

	@GetMapping(value = { "add-guest", "/add-guest.html" })
	public String addguest(Model model) {
		model.addAttribute("user", new User());
		return "add-guest";
	}

	@PostMapping("/add-guest")
	public String greetingSubmit(@ModelAttribute User user, Model model,
			@RequestParam("image") MultipartFile multipartFile, RedirectAttributes redirectAttributes) {

		String fileName = "";
		user.setCreatedDate(new Date());
		if (multipartFile != null && multipartFile.getOriginalFilename() != null) {
			fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setPhoto(fileName);
			user.setActive(true);
			User savedUser = repo.save(user);
			String uploadDir = "user-photos/" + savedUser.getId();
			try {
				FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			} catch (IOException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("message", "Operation failed. Please try again");
				redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

			}
		} else {
			user = repo.save(user);
		}
		model.addAttribute("user", new User());

		redirectAttributes.addFlashAttribute("message", "Operation performed successfully.");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		return "redirect:/add-guest.html";
	}
	
	
	@PostMapping(value = {"delete-user", "/delete-user"})
	public String deleteUser(@RequestParam String uid, RedirectAttributes redirectAttributes) {
		if(uid!=null && !uid.isEmpty()) {
			
			//Soft Delete code
			Long id = Long.parseLong(uid.toString());
			User user = repo.findByIdAndActive(id, true);
			if(user!=null) {
				user.setActive(false);
				repo.save(user);
				redirectAttributes.addFlashAttribute("message", "Operation performed successfully.");
				redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			}else {
				redirectAttributes.addFlashAttribute("message", "User Already Deleted.");
				redirectAttributes.addFlashAttribute("alertClass", "alert-success");
			}
		}

		return "redirect:/index.html";
	}
}
