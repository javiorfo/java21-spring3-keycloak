package com.jo.adapter.in;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jo.application.in.FindDummyUseCase;
import com.jo.application.in.SaveDummyUseCase;
import com.jo.domain.model.Dummy;

@WebMvcTest(controllers = DummyController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DummyControllerTest {
    
    @Autowired
	private MockMvc mockMvc;

	@MockBean
	private  FindDummyUseCase findDummyUseCase;
	
    @MockBean
	private  SaveDummyUseCase saveDummyUseCase;

	@Test
	void findDummy() throws Exception {
        Mockito.when(findDummyUseCase.findById(1)).thenReturn(new Dummy("dummy"));

		mockMvc.perform(get("/dummy/{id}", 1)
				.header("Content-Type", "application/json"))
				.andExpect(status().isOk());

		then(findDummyUseCase).should().findById(eq(1));
	}
}
