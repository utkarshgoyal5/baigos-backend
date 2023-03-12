package com.utkarsh.baigos.services.impl;

import com.utkarsh.baigos.payloads.PropertyDto;
import com.utkarsh.baigos.repositories.PropertyRepo;
import com.utkarsh.baigos.services.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepo propertyRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PropertyDto createProperty(PropertyDto propertyDto) {
        return null;
    }

    @Override
    public PropertyDto updateProperty(PropertyDto propertyDto) {
        return null;
    }

    @Override
    public PropertyDto getPropertyById(int propertyId) {
        return null;
    }

    @Override
    public List<PropertyDto> getAllProperty() {
        return null;
    }

    @Override
    public void deleteProperty(int propertyId) {

    }

    @Override
    public List<PropertyDto> getPropertyByCategory(int categoryId) {
        return null;
    }

    @Override
    public List<PropertyDto> getPropertyByUser(int userId) {
        return null;
    }
}
