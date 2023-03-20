package com.utkarsh.baigos.controllers;

import com.utkarsh.baigos.config.AppConstant;
import com.utkarsh.baigos.payloads.*;
import com.utkarsh.baigos.services.FileService;
import com.utkarsh.baigos.services.PropertyService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("api")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @Autowired
    FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/properties")
    public ResponseEntity<PropertyDto> createProperty(@RequestBody PropertyDto propertyDto) {

        PropertyDto createPropertyDto = this.propertyService.createProperty(propertyDto);

        return new ResponseEntity<PropertyDto>(createPropertyDto, HttpStatus.CREATED);

    }

    @GetMapping("/category/{category_id}/properties")
    public ResponseEntity<List<PropertyDto>> getPropertyByCategory(@PathVariable("category_id") int categoryId) {

        List<PropertyDto> propertyDtoList = this.propertyService.getPropertyByCategory(categoryId);

        return new ResponseEntity<List<PropertyDto>>(propertyDtoList, HttpStatus.OK);
    }

    @GetMapping("/user/{user_id}/properties")
    public ResponseEntity<List<PropertyDto>> getPropertyByUser(@PathVariable("user_id") int userId) {

        List<PropertyDto> propertyDtoList = this.propertyService.getPropertyByUser(userId);

        return new ResponseEntity<List<PropertyDto>>(propertyDtoList, HttpStatus.OK);
    }

    @GetMapping("/properties/{property_id}")
    public ResponseEntity<PropertyDto> getProperty(@PathVariable("property_id") Integer propertyId) {
        PropertyDto getPropertyDto = propertyService.getPropertyById(propertyId);
        return new ResponseEntity<>(getPropertyDto, HttpStatus.OK);
    }

    @GetMapping("/properties")
    public ResponseEntity<PropertyResponse> getAllProperties(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir) {
        PropertyResponse propertyResponse = propertyService.getAllProperty(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(propertyResponse, HttpStatus.OK);
    }

    @DeleteMapping("/properties/{property_id}")
    public ResponseEntity<ApiResponse> deleteProperty(@PathVariable("property_id") Integer propertyId) {
        propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>(new ApiResponse("Property deleted successfully", "2000", true), HttpStatus.OK);
    }

    @PutMapping("/properties/{property_id}")
    public ResponseEntity<PropertyDto> updateProperty(@RequestBody PropertyDto propertyDto, @PathVariable("property_id") Integer propertyId) {

        PropertyDto updatedPropertyDto = this.propertyService.updateProperty(propertyDto,propertyId);

        return new ResponseEntity<PropertyDto>(updatedPropertyDto, HttpStatus.OK);
    }

    @GetMapping("/properties/search/{keyword}")
    public ResponseEntity<List<PropertyDto>> getProperty(@PathVariable("keyword") String keyword) {
        List<PropertyDto> listOfProperties = propertyService.searchProperty(keyword);
        return new ResponseEntity<>(listOfProperties, HttpStatus.OK);
    }

    @PostMapping("/properties/upload/{propertyId}")
    public ResponseEntity<PropertyDto> uploadFile(@RequestParam("image") MultipartFile image,
        @PathVariable Integer propertyId) throws IOException {

        String fileName = null;

        fileName = this.fileService.uploadImage(path, image);
        PropertyDto propertyDto = this.propertyService.getPropertyById(propertyId);
        propertyDto.setImageName(fileName);
        PropertyDto updatedPropertyDto = this.propertyService.updateProperty(propertyDto, propertyId);


        return new ResponseEntity<>(updatedPropertyDto, HttpStatus.OK);
    }

    @GetMapping(value = "/properties/images/{propertyId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable Integer propertyId, HttpServletResponse response) throws IOException {
        PropertyDto propertyDto = this.propertyService.getPropertyById(propertyId);
        String imageName = propertyDto.getImageName();
        InputStream inputStream = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream, response.getOutputStream());
    }

}
