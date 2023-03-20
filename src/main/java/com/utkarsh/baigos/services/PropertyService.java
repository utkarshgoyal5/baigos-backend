package com.utkarsh.baigos.services;

import com.utkarsh.baigos.payloads.PropertyDto;
import com.utkarsh.baigos.payloads.PropertyResponse;

import java.util.List;

public interface PropertyService {

    PropertyDto createProperty(PropertyDto propertyDto);
    PropertyDto updateProperty(PropertyDto propertyDto, Integer propertyId);
    PropertyDto getPropertyById(int propertyId);
    PropertyResponse getAllProperty(int pageNumber, int pageSize, String sortBy, String sortDir);
    void deleteProperty(int propertyId);
    List<PropertyDto> getPropertyByCategory(int categoryId);
    List<PropertyDto> getPropertyByUser(int userId);
    List<PropertyDto> searchProperty(String keyword);
}
