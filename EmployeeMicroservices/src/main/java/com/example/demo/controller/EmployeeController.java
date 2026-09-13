package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService= employeeService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto  employeeDto){
		EmployeeDto response=employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id){
		EmployeeDto response =employeeService.updateEmployee(id, employeeDto);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delteEmployee(@PathVariable Long id){
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>( "EMployee deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<EmployeeDto> getSingleEmployee(@PathVariable Long id){
		EmployeeDto response=employeeService.getSingleEmployee(id);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Iterable<EmployeeDto>> getAllEmployees(){
		Iterable<EmployeeDto> response=employeeService.getAllEmployees();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
