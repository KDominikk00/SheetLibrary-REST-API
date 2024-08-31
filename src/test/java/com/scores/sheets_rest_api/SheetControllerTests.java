package com.scores.sheets_rest_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scores.sheets_rest_api.entity.SheetEntity;
import com.scores.sheets_rest_api.service.SheetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class SheetControllerTests {

    private static final String END_POINT_PATH = "/sheet";
    private static final String USER_ID = "user123"; // Example user ID for tests

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SheetService sheetService;

    @Test
    public void findAll_shouldReturnEmptyList() throws Exception {
        when(sheetService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get(END_POINT_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void findById_shouldReturnNotFound() throws Exception {
        int sheetId = 1;
        when(sheetService.findById(sheetId)).thenReturn(Optional.empty());

        mockMvc.perform(get(END_POINT_PATH + "/" + sheetId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findById_shouldReturnSheet() throws Exception {
        int sheetId = 1;
        SheetEntity sheet = new SheetEntity(sheetId, "Title", "Author", "Description", "http://url.com", "http://thumburl.com", 1, USER_ID);
        when(sheetService.findById(sheetId)).thenReturn(Optional.of(sheet));

        mockMvc.perform(get(END_POINT_PATH + "/" + sheetId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Title"))
                .andExpect(jsonPath("$.author").value("Author"))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.sheeturl").value("http://url.com"))
                .andExpect(jsonPath("$.sheetthumb").value("http://thumburl.com"))
                .andExpect(jsonPath("$.difficulty").value(1));
    }

    @Test
    public void save_shouldReturnBadRequest() throws Exception {
        SheetEntity newSheet = new SheetEntity();
        newSheet.setTitle("");
        newSheet.setAuthor("");
        newSheet.setDescription("");
        newSheet.setSheeturl("");
        newSheet.setSheetthumb("");
        newSheet.setDifficulty(0);
        newSheet.setUserId(USER_ID); // Set user ID for creation

        String requestBodyJSON = objectMapper.writeValueAsString(newSheet);

        mockMvc.perform(post(END_POINT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyJSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void save_shouldReturnCreatedSheet() throws Exception {
        SheetEntity newSheet = new SheetEntity(0, "Title", "Author", "Description", "http://url.com", "http://thumburl.com", 1, USER_ID);
        SheetEntity savedSheet = new SheetEntity(1, "Title", "Author", "Description", "http://url.com", "http://thumburl.com", 1, USER_ID);

        when(sheetService.save(any(SheetEntity.class))).thenReturn(savedSheet);

        String requestBodyJSON = objectMapper.writeValueAsString(newSheet);

        mockMvc.perform(post(END_POINT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyJSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Title"))
                .andExpect(jsonPath("$.author").value("Author"))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.sheeturl").value("http://url.com"))
                .andExpect(jsonPath("$.sheetthumb").value("http://thumburl.com"))
                .andExpect(jsonPath("$.difficulty").value(1));
    }

    @Test
    public void update_shouldReturnUpdatedSheet() throws Exception {
        SheetEntity updatedSheet = new SheetEntity(1, "Updated Title", "Updated Author", "Updated Description", "http://updated-url.com", "http://updated-thumburl.com", 2, USER_ID);

        when(sheetService.update(any(SheetEntity.class))).thenReturn(updatedSheet);

        String requestBodyJSON = objectMapper.writeValueAsString(updatedSheet);

        mockMvc.perform(put(END_POINT_PATH + "/" + updatedSheet.getId())
                        .header("userId", USER_ID) // Include user ID in the request header
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyJSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.author").value("Updated Author"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.sheeturl").value("http://updated-url.com"))
                .andExpect(jsonPath("$.sheetthumb").value("http://updated-thumburl.com"))
                .andExpect(jsonPath("$.difficulty").value(2));
    }

    @Test
    public void delete_shouldReturnNoContent() throws Exception {
        int sheetId = 1;

        mockMvc.perform(delete(END_POINT_PATH + "/" + sheetId)
                        .header("userId", USER_ID) // Include user ID in the request header
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}