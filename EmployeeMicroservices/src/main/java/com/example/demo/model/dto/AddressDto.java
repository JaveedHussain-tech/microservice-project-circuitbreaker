package com.example.demo.model.dto;

import com.example.demo.model.dto.AddressType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class AddressDto {
	
	
	private Long id;
	private Long EMPId;
	private String street;
	private Long pincode;
	private String city;
	private String country;
	
	public AddressDto() {
	}
	
	public AddressDto(Long id, Long eMPId, String street, Long pincode, String city, String country,
			AddressType addresstype) {
		super();
		this.id = id;
		EMPId = eMPId;
		this.street = street;
		this.pincode = pincode;
		this.city = city;
		this.country = country;
		this.addresstype = addresstype;
	}

	@Override
	public String toString() {
		return "Address [Id=" + id + ", EMPId=" + EMPId + ", street=" + street + ", pincode=" + pincode + ", city="
				+ city + ", country=" + country + ", addresstype=" + addresstype + "]";
	}

	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}

	public Long getEMPId() {
		return EMPId;
	}

	public void setEMPId(Long eMPId) {
		EMPId = eMPId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public AddressType getAddresstype() {
		return addresstype;
	}

	public void setAddresstype(AddressType addresstype) {
		this.addresstype = addresstype;
	}

	@Enumerated(EnumType.STRING)
    private AddressType addresstype;

}
