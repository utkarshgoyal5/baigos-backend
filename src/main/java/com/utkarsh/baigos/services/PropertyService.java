package com.utkarsh.baigos.services;

import com.utkarsh.baigos.payloads.PropertyDto;

import java.util.List;

public interface PropertyService {

    PropertyDto createProperty(PropertyDto propertyDto);
    PropertyDto updateProperty(PropertyDto propertyDto);
    PropertyDto getPropertyById(int propertyId);
    List<PropertyDto> getAllProperty();
    void deleteProperty(int propertyId);
    List<PropertyDto> getPropertyByCategory(int categoryId);
    List<PropertyDto> getPropertyByUser(int userId);


}
