package com.example.doggoApp.doggoApp;

import com.example.doggoApp.doggoApp.domain.Animal;
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
		Animal animal = new Animal();
		animal.setId(1L);
		animal.setIsDeleted(false);
		when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));
		Animal result = animalService.getById(1L);
		assertNotNull(result);
		assertEquals(1L, result.getId());
	}

	@Test
	void givenDeletedAnimal_whenGetById_thenThrowException() {
		Animal animal = new Animal();
		animal.setId(1L);
		animal.setIsDeleted(true);
		when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));
		assertThrows(NoSuchElementException.class, () -> animalService.getById(1L));
	}

	@Test
	void givenNewAnimal_whenCreateAnimal_thenAnimalCreated() {
		Animal animal = new Animal();
		animal.setId(1L);
		when(animalRepository.save(any(Animal.class))).thenReturn(animal);
		Animal result = animalService.create(new Animal());
		assertNotNull(result);
		assertEquals(1L, result.getId());
	}

	@Test
	void givenExistingAnimal_whenUpdateAnimal_thenAnimalUpdated() {
		Long animalId = 1L;
		Animal existingAnimal = new Animal();
		existingAnimal.setId(animalId);
		when(animalRepository.save(any(Animal.class))).thenReturn(existingAnimal);
		Animal result = animalService.update(animalId, new Animal());
		assertNotNull(result);
		assertEquals(animalId, result.getId());
	}
}
