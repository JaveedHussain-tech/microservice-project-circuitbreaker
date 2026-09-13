package com.example.demo.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.AddressRepository;
import com.example.demo.Service.AddressService;
import com.example.demo.client.EmployeeClient;
import com.example.demo.model.Dto.AddressDto;
import com.example.demo.model.Dto.AddressRequest;
import com.example.demo.model.Dto.AddressRequestDto;
import com.example.demo.model.Entity.Address;



@Service
public class AddressServiceimpl implements AddressService {
	
	private final AddressRepository addressRepository;

	private final ModelMapper modelMapper;
	
	private final EmployeeClient employeeclient;
	
	
	
	Logger log=LoggerFactory.getLogger(AddressServiceimpl.class);
	
	public AddressServiceimpl(AddressRepository addressRepository,ModelMapper modelMapper,EmployeeClient employeeclient) {
		this.addressRepository=addressRepository;
		this.modelMapper = modelMapper;
		this.employeeclient=employeeclient;
	}

	@Override
	public List<AddressDto> saveaddress(AddressRequest addressrequest) {
		// TODO check if employee exists
		System.out.println("Employee ID being sent to Feign: "
		        + addressrequest.getEmpid());
		employeeclient.getSingleEmployee(addressrequest.getEmpid());
		
	  List<Address> listtosave= this.saveorUpdateAddress(addressrequest);
		
		List<Address> savedAddress= addressRepository.saveAll(listtosave);
		return savedAddress.stream().map( address ->modelMapper.map(address, AddressDto.class)).toList();
	}

	@Override
	public List<AddressDto> updateaddress(AddressRequest addressrequest) {
		// TODO Auto-generated method stub
		System.out.println("Employee ID being sent to Feign: "
		        + addressrequest.getEmpid());
		employeeclient.getSingleEmployee(addressrequest.getEmpid());
		List<Address> addressbyEmpid=addressRepository.findAllByEmpid(addressrequest.getEmpid());
		if(addressbyEmpid.isEmpty()) {
			log.info("No adress found for employee id{}",addressrequest.getEmpid());
			log.info("Creating new address for employee id{}", addressrequest.getEmpid());
		}
		
		List<Address> ListtoUpdate=this.saveorUpdateAddress(addressrequest);
		
		List<Long> upcomingNonNullIds =ListtoUpdate.stream().map(Address::getId).filter(Objects :: nonNull).toList();
		List<Long> existingIds= addressbyEmpid.stream().map(Address::getId).toList();
		
		List<Long> idsTodelete=existingIds.stream().filter(id -> !upcomingNonNullIds.contains(id)).toList();
		if(idsTodelete.isEmpty()) {
			addressRepository.deleteAllById(idsTodelete);
		}
		
		List<Address> updateAddress = addressRepository.saveAll(ListtoUpdate);
		
		return updateAddress.stream().map(address -> modelMapper.map(address,  AddressDto.class)).toList();
	
	}

	 @Override
	    public AddressDto getsingleAddress(Long id) {
	        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + id,HttpStatus.NOT_FOUND));
	        return modelMapper.map(address, AddressDto.class);
	    }


	@Override
	public List<AddressDto> getAllAddress() {
		// TODO Auto-generated method stub
		
			List<Address> address=addressRepository.findAll();
			return address.stream().map((Address add) -> modelMapper.map(add, AddressDto.class)).toList();
	
	}

	@Override
	@Transactional
	public String deleteAddress(Long empid) {

	    // Step 1: Check whether employee exists
	    employeeclient.getSingleEmployee(empid);

	    // Step 2: Find all addresses belonging to this employee
	    List<Address> addressList =
	            addressRepository.findAllByEmpid(empid);

	    // Step 3: Employee exists, but no address exists
	    if (addressList.isEmpty()) {
	        throw new ResourceNotFoundException(
	                "No address found for employee id: " + empid,
	                HttpStatus.NOT_FOUND
	        );
	    }
	    
	    
	 // Step 4: Delete all addresses belonging to employee
	    addressRepository.deleteAllByEmpid(empid);

	    // Step 5: Return success message
	    return "Employee id " + empid +
	           " address deleted successfully";
	}
	
	  @Override
	    public List<AddressDto> getAddressByEmpid(Long empid) {
//		  try {
//	           Thread.sleep(6000);
//	       } catch (InterruptedException e) {
//	           throw new RuntimeException(e);
//	       }
	        
	        List<Address> addressByEmpId = addressRepository.findAllByEmpid(empid);
	        if(addressByEmpId.isEmpty()){
	            throw new ResourceNotFoundException("No address found for employee id: " + empid,HttpStatus.NOT_FOUND);
	        }
	        return addressByEmpId.stream().map(address -> modelMapper.map(address, AddressDto.class)).toList();
	    }
	    
	    
	
	private List<Address> saveorUpdateAddress(AddressRequest addressrequest){
		
		List<Address> savetoList =new ArrayList<>();
		for(AddressRequestDto addressrequestdto : addressrequest.getAddressrequestdto()) {
			Address address= new Address();
			
			address.setId(addressrequestdto.getId() !=null ? addressrequestdto.getId() : null);
			address.setStreet(addressrequestdto.getStreet());
			address.setCity(addressrequestdto.getCity());
			address.setCountry(addressrequestdto.getCountry());
			address.setPincode(addressrequestdto.getPincode());
			address.setAddresstype(addressrequestdto.getAddresstype());
			address.setEmpid(addressrequest.getEmpid());
			savetoList.add(address);
		}
		
		return savetoList;
		
	
	}

}
