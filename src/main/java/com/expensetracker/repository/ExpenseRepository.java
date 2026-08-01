package com.expensetracker.repository;

import com.expensetracker.model.Expense;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ExpenseRepository {

    private final ConcurrentHashMap<Long, Expense> expenseStore = new ConcurrentHashMap<>();

    private final AtomicLong idGenerator = new AtomicLong(0);

    // Save Expense
    public Expense save(Expense expense) {

        if (expense.getId() == null) {
            expense.setId(idGenerator.incrementAndGet());
        }

        expenseStore.put(expense.getId(), expense);

        return expense;
    }

    // Get All Expenses
    public List<Expense> findAll() {
        return new ArrayList<>(expenseStore.values());
    }

    // Find Expense By ID
    public Optional<Expense> findById(Long id) {
        return Optional.ofNullable(expenseStore.get(id));
    }

    // Delete Expense
    public void deleteById(Long id) {
        expenseStore.remove(id);
    }
}