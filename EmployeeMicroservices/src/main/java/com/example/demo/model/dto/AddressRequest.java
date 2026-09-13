package com.example.demo.model.dto;

import java.util.ArrayList;
import java.util.List;

public class AddressRequest {
	
	private Long empid;
	private List<AddressRequestDto> addressrequestdto ;

	public Long getEmpid() {
		return empid;
	}
	public void setEmpid(Long empid) {
		this.empid = empid;
	}
	public List<AddressRequestDto> getAddressrequestdto() {
		return addressrequestdto;
	}
	public void setAddressrequestdto(List<AddressRequestDto> addressrequestdto) {
		this.addressrequestdto = addressrequestdto;
	}

}
