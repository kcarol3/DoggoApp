package com.example.doggoApp.doggoApp;

import com.example.doggoApp.doggoApp.domain.Animal;
import com.example.doggoApp.doggoApp.domain.Dog;
import com.example.doggoApp.doggoApp.repository.AnimalRepository;
import com.example.doggoApp.doggoApp.service.impl.AnimalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AnimalServiceImplTest {

	@Mock
	private AnimalRepository animalRepository;

	@InjectMocks
	private AnimalServiceImpl animalService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void givenExistingAnimal_whenGetById_thenReturnAnimal() {
		Dog dog = new Dog();
		dog.setId(1L);
		dog.setIsDeleted(false);

		when(animalRepository.findById(1L)).thenReturn(Optional.of(dog));
		Animal result = animalService.getById(1L);
		assertNotNull(result);
		assertEquals(1L, result.getId());
	}

	@Test
	void givenDeletedAnimal_whenGetById_thenThrowException() {
		Dog dog = new Dog();
		dog.setId(1L);
		dog.setIsDeleted(true);
		when(animalRepository.findById(1L)).thenReturn(Optional.of(dog));
		assertThrows(NoSuchElementException.class, () -> animalService.getById(1L));
	}

	@Test
	void givenNewAnimal_whenCreateAnimal_thenAnimalCreated() {
		Dog dog = new Dog();
		dog.setId(1L);
		when(animalRepository.save(any(Animal.class))).thenReturn(dog);
		Animal result = animalService.create(new Dog());
		assertNotNull(result);
		assertEquals(1L, result.getId());
	}

	@Test
	void givenExistingAnimal_whenUpdateAnimal_thenAnimalUpdated() {
		Long animalId = 1L;
		Dog existingDog = new Dog();
		existingDog.setId(animalId);
		when(animalRepository.save(any(Animal.class))).thenReturn(existingDog);
		Animal result = animalService.update(animalId, new Dog());
		assertNotNull(result);
		assertEquals(animalId, result.getId());
	}
}
