package com.example.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.sms.entity.Student;
import com.example.sms.service.StudentService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class StudentController {
	
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		
		//create student object to hold student form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		
		return "redirect:/students";
		}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		
		model.addAttribute("student", studentService.getStudentById(id));
		
		return "edit_student";
		
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {
		
		
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setLastName(student.getLastName());
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setEmail(student.getEmail());
		
		studentService.updateStudent(existingStudent);
		
		
		return "redirect:/students";
		
		
	}
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		
		return "redirect:/students";
	}

}













