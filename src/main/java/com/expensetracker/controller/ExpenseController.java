package com.expensetracker.controller;

import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.dto.ExpenseResponse;
import com.expensetracker.service.ExpenseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;



@RestController
@RequestMapping("/expenses")
@Tag(
    name = "Expense API",
    description = "Operations for managing expenses"
)
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // Add Expense
    @Operation(summary = "Add a new expense")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseResponse addExpense(@Valid @RequestBody ExpenseRequest request) {
        return expenseService.addExpense(request);
    }

    // Get All Expenses
    @Operation(summary = "Get all expenses")
    @GetMapping
    public List<ExpenseResponse> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    // Get Expenses By Category
    @Operation(summary = "Get expenses by category")
    @GetMapping("/category/{category}")
    public List<ExpenseResponse> getExpensesByCategory(
            @PathVariable String category) {

        return expenseService.getExpensesByCategory(category);
    }

    // Overall Total
    @Operation(summary = "Get total expenses")
    @GetMapping("/total")
    public BigDecimal getTotalExpenses() {
        return expenseService.getTotalExpenses();
    }

    // Category Total
    @Operation(summary = "Get total expenses by category")
    @GetMapping("/total/{category}")
    public BigDecimal getTotalExpensesByCategory(
            @PathVariable String category) {

        return expenseService.getTotalExpensesByCategory(category);
    }

    // Delete Expense
    @Operation(summary = "Delete an expense")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpense(
            @PathVariable Long id) {

        expenseService.deleteExpense(id);
    }

}