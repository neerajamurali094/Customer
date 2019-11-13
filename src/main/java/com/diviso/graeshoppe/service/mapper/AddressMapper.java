package com.diviso.graeshoppe.service.mapper;

import com.diviso.graeshoppe.domain.*;
import com.diviso.graeshoppe.service.dto.AddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Address and its DTO AddressDTO.
 */
@Mapper(componentModel = "spring", uses = {CustomerMapper.class, CountryMapper.class})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

    @Override
	@Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "country.id", target = "countryId")
    AddressDTO toDto(Address address);

    @Override
	@Mapping(source = "customerId", target = "customer")
    @Mapping(source = "countryId", target = "country")
    Address toEntity(AddressDTO addressDTO);

    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
