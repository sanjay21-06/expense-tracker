package com.expensetracker.controller;

import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.dto.ExpenseResponse;
import com.expensetracker.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseService expenseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should create a new expense")
    void shouldAddExpense() throws Exception {

        ExpenseRequest request = new ExpenseRequest(
                "Pizza",
                new BigDecimal("450"),
                "Food",
                LocalDate.now());

        ExpenseResponse response = new ExpenseResponse(
                1L,
                "Pizza",
                new BigDecimal("450"),
                "Food",
                request.getDate());

        when(expenseService.addExpense(any()))
                .thenReturn(response);

        mockMvc.perform(post("/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Pizza"))
                .andExpect(jsonPath("$.category").value("Food"));
    }

    @Test
    @DisplayName("Should return all expenses")
    void shouldGetAllExpenses() throws Exception {

        ExpenseResponse response = new ExpenseResponse(
                1L,
                "Pizza",
                new BigDecimal("450"),
                "Food",
                LocalDate.now());

        when(expenseService.getAllExpenses())
                .thenReturn(List.of(response));

        mockMvc.perform(get("/expenses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Pizza"));
    }

    @Test
    @DisplayName("Should delete expense")
    void shouldDeleteExpense() throws Exception {

        doNothing().when(expenseService).deleteExpense(1L);

        mockMvc.perform(delete("/expenses/1"))
                .andExpect(status().isNoContent());
    }

}