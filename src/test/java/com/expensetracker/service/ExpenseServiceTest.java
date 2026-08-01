package com.expensetracker.service;

import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.dto.ExpenseResponse;
import com.expensetracker.model.Expense;
import com.expensetracker.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    void shouldAddExpenseSuccessfully() {

        ExpenseRequest request = new ExpenseRequest(
            "Pizza",
            new BigDecimal("450"),
            "Food",
            LocalDate.now()
        );

        Expense savedExpense = Expense.builder()
            .id(1L)
            .title("Pizza")
            .amount(new BigDecimal("450"))
            .category("Food")
            .date(request.getDate())
            .build();

        when(expenseRepository.save(org.mockito.ArgumentMatchers.any(Expense.class)))
            .thenReturn(savedExpense);

        ExpenseResponse response = expenseService.addExpense(request);

        assertEquals(1L, response.getId());
        assertEquals("Pizza", response.getTitle());
        assertEquals("Food", response.getCategory());
        assertEquals(new BigDecimal("450"), response.getAmount());
    }

    @Test
void shouldReturnAllExpenses() {

    Expense expense1 = Expense.builder()
            .id(1L)
            .title("Pizza")
            .amount(new BigDecimal("450"))
            .category("Food")
            .date(LocalDate.now())
            .build();

    Expense expense2 = Expense.builder()
            .id(2L)
            .title("Laptop")
            .amount(new BigDecimal("50000"))
            .category("Shopping")
            .date(LocalDate.now())
            .build();

    when(expenseRepository.findAll())
            .thenReturn(List.of(expense1, expense2));

    List<ExpenseResponse> expenses = expenseService.getAllExpenses();

    assertEquals(2, expenses.size());
}

}