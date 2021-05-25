package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExpenseManagerTest {

    List<Expense> createList() {
        List<Expense> expenseList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            expenseList.add(new Expense(8));
        }
        return expenseList;
    }

    @Test
    void testCalculateTotal() {
        ExpenseRepository expenseRepository = mock(ExpenseRepository.class);
        List<Expense> expenseList = createList();
        when(expenseRepository.getExpenses()).thenReturn(expenseList);
        ExpenseManager expenseManager = new ExpenseManager(expenseRepository, new FancyService());
        assertEquals(24, expenseManager.calculateTotal());
        when(expenseRepository.getExpensesByCategory("Food")).thenReturn(Collections.emptyList());
        when(expenseRepository.getExpensesByCategory("Sport")).thenReturn(Collections.emptyList());
        when(expenseRepository.getExpensesByCategory("Car")).thenReturn(createList());
        when(expenseRepository.getExpensesByCategory("Home")).thenReturn(createList());
        assertEquals(0, expenseManager.calculateTotalForCategory("Sport"));
    }

    @Test
    void testCalculateTotalInDollars() throws ConnectException {
        ExpenseRepository expenseRepository = mock(ExpenseRepository.class);
        List<Expense> expenseList = createList();
        when(expenseRepository.getExpenses()).thenReturn(expenseList);
        FancyService fancyService = mock(FancyService.class);
        when(fancyService.convert(24, "PLN", "USD")).thenReturn(6.0);
        when(fancyService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(
                (Answer) invocation -> {
                    double number = invocation.getArgument(0);
                    return  number/  4.0;
                });
        ExpenseManager expenseManager = new ExpenseManager(expenseRepository, fancyService);
        assertEquals(6.0, expenseManager.calculateTotalInDollars());


    }

}
