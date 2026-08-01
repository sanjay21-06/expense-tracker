package com.expensetracker.service;

import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.dto.ExpenseResponse;
import com.expensetracker.model.Expense;
import com.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import com.expensetracker.exception.ExpenseNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    // Add Expense
    public ExpenseResponse addExpense(ExpenseRequest request) {

        Expense expense = Expense.builder()
                .title(request.getTitle())
                .amount(request.getAmount())
                .category(request.getCategory())
                .date(request.getDate())
                .build();

        Expense savedExpense = expenseRepository.save(expense);

        return mapToResponse(savedExpense);
    }

    // Get All Expenses
    public List<ExpenseResponse> getAllExpenses() {

        return expenseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Filter by Category
    public List<ExpenseResponse> getExpensesByCategory(String category) {

        return expenseRepository.findAll()
                .stream()
                .filter(expense ->
                        expense.getCategory().equalsIgnoreCase(category))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Overall Total
    public BigDecimal getTotalExpenses() {

        return expenseRepository.findAll()
                .stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Total by Category
    public BigDecimal getTotalExpensesByCategory(String category) {

        return expenseRepository.findAll()
                .stream()
                .filter(expense ->
                        expense.getCategory().equalsIgnoreCase(category))
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Delete Expense
    public void deleteExpense(Long id) {

        expenseRepository.findById(id)
            .orElseThrow(() ->
                new ExpenseNotFoundException(
                    "Expense with ID " + id + " not found"));

        expenseRepository.deleteById(id);
    }

    // Convert Entity to Response DTO
    private ExpenseResponse mapToResponse(Expense expense) {

        return ExpenseResponse.builder()
                .id(expense.getId())
                .title(expense.getTitle())
                .amount(expense.getAmount())
                .category(expense.getCategory())
                .date(expense.getDate())
                .build();
    }
}