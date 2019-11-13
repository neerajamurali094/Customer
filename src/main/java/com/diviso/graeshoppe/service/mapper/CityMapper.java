package com.diviso.graeshoppe.service.mapper;

import com.diviso.graeshoppe.domain.*;
import com.diviso.graeshoppe.service.dto.CityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity City and its DTO CityDTO.
 */
@Mapper(componentModel = "spring", uses = {StateMapper.class})
public interface CityMapper extends EntityMapper<CityDTO, City> {

    @Override
	@Mapping(source = "state.id", target = "stateId")
    CityDTO toDto(City city);

    @Override
	@Mapping(source = "stateId", target = "state")
    City toEntity(CityDTO cityDTO);

    default City fromId(Long id) {
        if (id == null) {
            return null;
        }
        City city = new City();
        city.setId(id);
        return city;
    }
}
