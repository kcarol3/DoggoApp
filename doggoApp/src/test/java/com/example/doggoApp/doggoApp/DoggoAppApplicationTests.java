package com.example.doggoApp.doggoApp.controller;

import com.example.doggoApp.doggoApp.domain.Dog;
import com.example.doggoApp.doggoApp.model.DogDTO;
import com.example.doggoApp.doggoApp.service.AnimalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogControllerTest {

	@Mock
	private AnimalService animalService;

	@InjectMocks
	private DogController dogController;

	private MockMvc mockMvc;

	@Test
	void whenCreateDog_thenReturnSuccessMessage() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(dogController).build();

		DogDTO dogDTO = new DogDTO();
		dogDTO.setName("Buddy");
		dogDTO.setBreed("Labrador");

		Dog dog = new Dog();
		dog.setName("Buddy");
		dog.setBreed("Labrador");

		when(animalService.create(any(Dog.class))).thenReturn(dog);

		mockMvc.perform(MockMvcRequestBuilders.post("/dog")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Buddy\",\"breed\":\"Labrador\",\"isSterilized\":false,\"isVaccinated\":true}")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string("Success created"));
	}
}
