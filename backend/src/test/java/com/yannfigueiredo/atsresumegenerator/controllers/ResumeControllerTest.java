package com.yannfigueiredo.atsresumegenerator.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yannfigueiredo.atsresumegenerator.Factory;
import com.yannfigueiredo.atsresumegenerator.models.Resume;
import com.yannfigueiredo.atsresumegenerator.services.ResumeService;
import com.yannfigueiredo.atsresumegenerator.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ResumeController.class)
public class ResumeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ResumeService resumeService;

    Long existingId;
    Long nonExistingId;
    Long integrityViolationId;
    Resume resume;
    Resume invalidResume;

    @BeforeEach
    public void setup() {
        existingId = 1L;
        nonExistingId = 999L;
        integrityViolationId = 2L;
        resume = Factory.createResume("Claudia Figueiredo");
        invalidResume = Factory.createResume("");
    }

    @Test
    public void findByIdShouldReturnResumeWhenIdExists() throws Exception {
        Mockito.when(resumeService.findById(existingId)).thenReturn(resume);

        ResultActions resultActions = mockMvc.perform(get("/resume/{id}", existingId).
                accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.name").exists());
    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        Mockito.when(resumeService.findById(nonExistingId)).thenThrow(ObjectNotFoundException.class);

        ResultActions resultActions = mockMvc.perform(get("/resume/{id}", nonExistingId).
                accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void createShouldReturnResumeAndCreatedWhenObjectIsValid() throws Exception {
        Mockito.when(resumeService.create(resume)).thenReturn(resume);

        String jsonBody = objectMapper.writeValueAsString(resume);

        ResultActions resultActions = mockMvc.perform(post("/resume").
                content(jsonBody).
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)
        );

        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.name").exists());
        resultActions.andExpect(jsonPath("$.telephoneNumber").exists());
    }

    @Test
    public void updateShouldReturnResumeAndOkWhenIdExists() throws Exception {
        Mockito.when(resumeService.update(
                        ArgumentMatchers.eq(existingId),
                        ArgumentMatchers.any())
                ).thenReturn(resume);

        String jsonBody = objectMapper.writeValueAsString(resume);

        ResultActions resultActions = mockMvc.perform(put("/resume/{id}", existingId).
                content(jsonBody).
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)
        );

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.name").exists());
        resultActions.andExpect(jsonPath("$.telephoneNumber").exists());
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        Mockito.when(resumeService.update(
                    ArgumentMatchers.eq(nonExistingId),
                    ArgumentMatchers.any()
                )).
                thenThrow(ObjectNotFoundException.class);

        String jsonBody = objectMapper.writeValueAsString(resume);

        ResultActions resultActions = mockMvc.perform(put("/resume/{id}", nonExistingId).
                content(jsonBody).
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON)
        );

        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() throws Exception {
        Mockito.doNothing().when(resumeService).delete(existingId);

        ResultActions resultActions = mockMvc.perform(delete("/resume/{id}", existingId));

        resultActions.andExpect(status().isNoContent());
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        Mockito.doThrow(ObjectNotFoundException.class).when(resumeService).delete(nonExistingId);

        ResultActions resultActions = mockMvc.perform(delete("/resume/{id}", nonExistingId));

        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void deleteShouldReturnConflictWhenIdViolatesIntegrity() throws Exception {
        Mockito.doThrow(DataIntegrityViolationException.class).when(resumeService).delete(integrityViolationId);

        ResultActions resultActions = mockMvc.perform(delete("/resume/{id}", integrityViolationId));

        resultActions.andExpect(status().isConflict());
    }
}
