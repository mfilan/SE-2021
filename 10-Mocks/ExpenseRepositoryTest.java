package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

import java.util.Collections;

public class ExpenseRepositoryTest {

    @Test
    void testLoadExpanses() {
        IFancyDatabase mockedDatabase = mock(MyDatabase.class);
        when(mockedDatabase.queryAll()).thenReturn(Collections.emptyList());
        ExpenseRepository expenseRepository = new ExpenseRepository(mockedDatabase);
        expenseRepository.loadExpenses();
        InOrder inOrder = Mockito.inOrder(mockedDatabase);
        inOrder.verify(mockedDatabase, times(1)).connect();
        inOrder.verify(mockedDatabase, times(1)).queryAll();
        inOrder.verify(mockedDatabase, times(1)).close();
        assertTrue(expenseRepository.getExpenses().isEmpty());
    }

    @Test
    void testSaveExpanses() {
        IFancyDatabase mockedDatabase = mock(MyDatabase.class);
        when(mockedDatabase.queryAll()).thenReturn(Collections.emptyList());
        ExpenseRepository expenseRepository = new ExpenseRepository(mockedDatabase);
        expenseRepository.loadExpenses();
        InOrder inOrder = Mockito.inOrder(mockedDatabase);
        inOrder.verify(mockedDatabase, times(1)).connect();
        inOrder.verify(mockedDatabase, times(1)).queryAll();
        inOrder.verify(mockedDatabase, times(1)).close();
        assertTrue(expenseRepository.getExpenses().isEmpty());
        for (int i = 0; i < 5; i++) {
            expenseRepository.addExpense(new Expense(1));
        }
        expenseRepository.saveExpenses();
        verify(mockedDatabase, times(5)).persist(argThat(Object -> Object.getClass() == Expense.class));
    }
}
