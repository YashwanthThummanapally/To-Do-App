package com.nit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nit.binding.ToDoTask;
import com.nit.binding.User;
import com.nit.binding.UserLogin;
import com.nit.entity.ToDoTaskEntity;
import com.nit.entity.UserEntity;
import com.nit.entity.UserLoginEntity;
import com.nit.repository.ToDoTaskRepository;
import com.nit.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ToDoController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ToDoTaskRepository toDoTaskRepository;
	
	@GetMapping("/loginForm")
	public String loadLoginForm(Model model) {
		
		model.addAttribute("userLogin", new UserLogin());
		
		return "login";
	}

	@GetMapping("/registerForm")
	public String loadRegistrationForm(Model model) {
		
		model.addAttribute("user", new User());
		
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid User user, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "register";
		}
		
	// save into DB
		UserEntity userEntity = new UserEntity();
		
		BeanUtils.copyProperties(user, userEntity);
		userEntity.setUserLoginEntity(new UserLoginEntity());
		userEntity.getUserLoginEntity().setEmail(user.getEmail());
		userEntity.getUserLoginEntity().setPassword(user.getPassword());
		
		// check if record is present
		Optional<UserEntity> optional = userRepository.findById(userEntity.getUserLoginEntity());
		if(optional.isPresent()) {
			model.addAttribute("msg", "User already exist");
		} else {
			userRepository.save(userEntity);			
			model.addAttribute("msg", "Registration Successful!");
		}
				
		return loadLoginForm(model);
	}
	
	@PostMapping("/login")
	public String loginUser(UserLogin userLogin, Model model, HttpServletRequest request) {
		
		UserLoginEntity userLoginEntity = new UserLoginEntity();
		
		BeanUtils.copyProperties(userLogin, userLoginEntity);
		
		Optional<UserEntity> optional = userRepository.findById(userLoginEntity);
		
		if(optional.isPresent()) {
		// login success
			UserEntity userEntity = optional.get();			
			// creating new session
			HttpSession session = request.getSession(); 
			session.setAttribute("userEntity", userEntity);
			
			return "todo";
		}else {
			model.addAttribute("msg", "Login Failed!");
			return loadLoginForm(model);
		}
	}
	
	@GetMapping("/createTask")
	public String createTask(Model model, HttpServletRequest request) {
		
		// access existing session
		HttpSession session = request.getSession(false);
		if(session==null) {
			model.addAttribute("msg", "Session Expired!");
			return loadLoginForm(model);
		}
		
		model.addAttribute("task", new ToDoTask()); // sending binding object to UI
		
		return "todoTask";
	}
	
	@PostMapping("/saveTask")
	public String saveToDoTask(ToDoTask task, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(false); // accessing existing session
		if(session==null) {
			model.addAttribute("msg", "Session Expired!");
			return loadLoginForm(model);
		}

		// store in DB
		UserEntity userEntity = (UserEntity)session.getAttribute("userEntity");		
		
		ToDoTaskEntity toDoTaskEntity = new ToDoTaskEntity();
		BeanUtils.copyProperties(task, toDoTaskEntity);
		
		// One User Many Tasks Mapping
		toDoTaskEntity.setUser(userEntity);		
		userEntity.setTask(toDoTaskEntity);
		
		toDoTaskRepository.save(toDoTaskEntity);
		
		model.addAttribute("msg", "To-Do Task Saved!");
		
		return "todo";
	}
	
	@GetMapping("/viewTask")
	public String viewAllTasks(Model model, HttpServletRequest request) {
	
		HttpSession session = request.getSession(false); // accessing existing session
		if(session==null) {
			model.addAttribute("msg", "Session Expired!");
			return loadLoginForm(model);
		}

		UserEntity userEntity = (UserEntity)session.getAttribute("userEntity");
		
		List<ToDoTaskEntity> tasks = toDoTaskRepository.findByUser(userEntity);
		
		model.addAttribute("tasks", tasks);
		
		return "viewTasks";
	}
	
	@GetMapping("/logout")
	public String logout(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(false); // accessing existing session
		if(session==null) {
			model.addAttribute("msg", "Session Expired!");
			return loadLoginForm(model);
		}

		session.removeAttribute("userEntity");
		session.invalidate();
		
		model.addAttribute("msg", "Loggedout Successfully!");
		
		return loadLoginForm(model);
	}
}
