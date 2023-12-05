package com.example.doggoApp.doggoApp.controller;

import com.example.doggoApp.doggoApp.domain.Dog;
import com.example.doggoApp.doggoApp.model.DogDTO;
import com.example.doggoApp.doggoApp.service.AnimalService;
import com.example.doggoApp.doggoApp.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

class DogControllerTest {

	@Mock
	private AnimalService animalService;

	@Mock
	private ImageService imageService;

	@InjectMocks
	private DogController dogController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getDog_withInvalidId_shouldReturnNotFoundResponse() {
		Long invalidId = 99L;
		when(animalService.getById(eq(invalidId))).thenThrow(new NoSuchElementException());

		ResponseEntity<DogDTO> responseEntity = dogController.getDog(invalidId);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(responseEntity.getBody()).isNull();
		verify(animalService, times(1)).getById(eq(invalidId));
	}

	@Test
	void updateDog_withValidData_shouldReturnSuccessResponse() {
		Long dogId = 1L;
		DogDTO dogDTO = new DogDTO();
		when(animalService.update(eq(dogId), any(Dog.class))).thenReturn(new Dog());

		ResponseEntity<String> responseEntity = dogController.updateDog(dogDTO, dogId);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isEqualTo("Success update");
		verify(animalService, times(1)).update(eq(dogId), any(Dog.class));
	}

	@Test
	void updateDog_withInvalidId_shouldReturnNotFoundResponse() {
		Long invalidId = 99L;
		when(animalService.update(eq(invalidId), any(Dog.class))).thenThrow(new NoSuchElementException());

		ResponseEntity<String> responseEntity = dogController.updateDog(new DogDTO(), invalidId);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(responseEntity.getBody()).isNull();
		verify(animalService, times(1)).update(eq(invalidId), any(Dog.class));
	}

	@Test
	void delete_withValidId_shouldReturnSuccessResponse() {
		Long dogId = 1L;
		Dog dog = new Dog();
		when(animalService.getById(eq(dogId))).thenReturn(dog);

		ResponseEntity<String> responseEntity = dogController.update(dogId);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isEqualTo("Success delete");
		verify(animalService, times(1)).delete(any(Dog.class));
	}

	@Test
	void delete_withInvalidId_shouldReturnNotFoundResponse() {
		Long invalidId = 99L;
		when(animalService.getById(eq(invalidId))).thenThrow(new NoSuchElementException());

		ResponseEntity<String> responseEntity = dogController.update(invalidId);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(responseEntity.getBody()).isNull();
		verify(animalService, times(0)).delete(any(Dog.class));
	}
}
