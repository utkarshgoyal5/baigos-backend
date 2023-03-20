package com.utkarsh.baigos.services.impl;

import com.utkarsh.baigos.entities.Category;
import com.utkarsh.baigos.entities.Property;
import com.utkarsh.baigos.entities.User;
import com.utkarsh.baigos.exceptions.ResourceNotFoundException;
import com.utkarsh.baigos.payloads.CategoryDto;
import com.utkarsh.baigos.payloads.PropertyDto;
import com.utkarsh.baigos.payloads.PropertyResponse;
import com.utkarsh.baigos.payloads.UserDto;
import com.utkarsh.baigos.repositories.CategoryRepo;
import com.utkarsh.baigos.repositories.PropertyRepo;
import com.utkarsh.baigos.repositories.UserRepo;
import com.utkarsh.baigos.services.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepo propertyRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PropertyDto createProperty(PropertyDto propertyDto) {

        User user = this.userRepo.findById(propertyDto.getUserId());
        if(null == user) {
            throw new ResourceNotFoundException("User", "id", propertyDto.getUserId());
        }

        Category category = this.categoryRepo.findByCategoryId(propertyDto.getCategoryId());
        if(null == category) {
            throw new ResourceNotFoundException("Category", "id", propertyDto.getCategoryId());
        }

        Property property = this.dtoToProperty(propertyDto);
        property.setImageName("default.png");
        property.setAddedDate(new Date());
        property.setUser(user);
        property.setCategory(category);

        Property createdProperty = propertyRepo.save(property);
        return this.modelMapper.map(createdProperty, PropertyDto.class);
    }

    @Override
    public PropertyDto updateProperty(PropertyDto propertyDto, Integer propertyId) {

        Property property = propertyRepo.findById(propertyId).orElseThrow(() -> new ResourceNotFoundException("Property", "property id", propertyId));
        property.setTitle(propertyDto.getTitle());
        property.setDescription(propertyDto.getDescription());
        property.setImageName(propertyDto.getImageName());
        Property updatedProperty = this.propertyRepo.save(property);
        return this.modelMapper.map(updatedProperty, PropertyDto.class);
    }

    @Override
    public PropertyDto getPropertyById(int propertyId) {

        Property property = propertyRepo.findById(propertyId).orElseThrow(() -> new ResourceNotFoundException("Property", "property id", propertyId));

        return this.modelMapper.map(property, PropertyDto.class);
    }

    @Override
    public PropertyResponse getAllProperty(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Property> pageProperty = propertyRepo.findAll(pageable);
        List<Property> allProperties = pageProperty.getContent();
        List<PropertyDto> allPropertiesDto = allProperties.stream().map((property) -> this.modelMapper.map(property, PropertyDto.class)).collect(Collectors.toList());
        PropertyResponse propertyResponse = new PropertyResponse();
        propertyResponse.setProperties(allPropertiesDto);
        propertyResponse.setPageNumber(pageNumber);
        propertyResponse.setPageSize(pageSize);
        propertyResponse.setTotalElement(pageProperty.getTotalElements());
        propertyResponse.setTotalPages(pageProperty.getTotalPages());
        propertyResponse.setLastPage(pageProperty.isLast());
        return propertyResponse;
    }

    @Override
    public void deleteProperty(int propertyId) {

        Property property = propertyRepo.findById(propertyId).orElseThrow(() -> new ResourceNotFoundException("Property", "property id", propertyId));

        propertyRepo.delete(property);
    }

    @Override
    public List<PropertyDto> getPropertyByCategory(int categoryId) {

        Category category = categoryRepo.findByCategoryId(categoryId);

        if(null == category){
            throw new ResourceNotFoundException("Category", "category id", categoryId);
        }

        List<Property> listOfProperties = propertyRepo.findByCategory(category);

        List<PropertyDto> listOfPropertiesDto = listOfProperties.stream().map((property) -> this.modelMapper.map(property, PropertyDto.class)).collect(Collectors.toList());

        return listOfPropertiesDto;
    }

    @Override
    public List<PropertyDto> getPropertyByUser(int userId) {

        User user = userRepo.findById(userId);

        if(null == user){
            throw new ResourceNotFoundException("User", "user id", userId);
        }

        List<Property> listOfProperties = propertyRepo.findByUser(user);

        List<PropertyDto> listOfPropertiesDto = listOfProperties.stream().map((property) -> this.modelMapper.map(property, PropertyDto.class)).collect(Collectors.toList());

        return listOfPropertiesDto;
    }

    @Override
    public List<PropertyDto> searchProperty(String keyword) {

        List<Property> propertyList = propertyRepo.findByTitleContaining(keyword);
        List<PropertyDto> listOfPropertiesDto = propertyList.stream().map((property) -> this.modelMapper.map(property, PropertyDto.class)).collect(Collectors.toList());

        return listOfPropertiesDto;
    }

    private Property dtoToProperty(PropertyDto propertyDto) {

        Property property = new Property();
        property.setTitle(propertyDto.getTitle());
        property.setDescription(propertyDto.getDescription());
        return property;
    }

//    private PropertyDto propertyToDto(Property property) {
//
//        PropertyDto propertyDto = new PropertyDto();
//        propertyDto.setTitle(property.getTitle());
//        propertyDto.setDescription(property.getDescription());
//        return propertyDto;
//    }

}
