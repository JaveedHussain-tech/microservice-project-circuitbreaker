package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.AddressService;
import com.example.demo.model.Dto.AddressDto;
import com.example.demo.model.Dto.AddressRequest;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	private final AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService=addressService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<List<AddressDto>> saveAddress(@RequestBody AddressRequest addressdto){
		List<AddressDto>response =addressService.saveaddress(addressdto);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<List<AddressDto>> updateAddress(@RequestBody AddressRequest addressrequest){
		List<AddressDto>response =addressService.updateaddress(addressrequest);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Iterable<AddressDto>> getAllAddress(){
		Iterable<AddressDto> response=addressService.getAllAddress();
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<AddressDto> getSingleAddress(@PathVariable Long id){
		AddressDto response=addressService.getsingleAddress(id);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAddress(@PathVariable Long id){
		 String response =
		            addressService.deleteAddress(id);
		return new ResponseEntity<>( response,HttpStatus.OK);
	}

	 @GetMapping("/empId/{empid}")
	    public ResponseEntity<List<AddressDto>> getAddressByEmpid(@PathVariable Long empid) {
	        List<AddressDto> response = addressService.getAddressByEmpid(empid);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
}
