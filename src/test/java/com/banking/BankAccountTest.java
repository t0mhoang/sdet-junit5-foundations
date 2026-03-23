package com.banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestMethodOrder(MethodOrderer.DisplayName.class)   // orders tests alphabetically in reports
class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("Alice", 1000.00);
    }

    //*******DEPOSIT TESTS

    @Test
    @DisplayName("Deposit: valid amount increases balance")
    void deposit_validAmount_increasesBalance() {
        account.deposit(500.00);
        assertEquals(1500.00, account.getBalance());
    }

    @Test
    @DisplayName("Deposit: zero amount throws exception")
    void deposit_zeroAmount_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
    }

    @Test
    @DisplayName("Deposit: negative amount throws exception")
    void deposit_negativeAmount_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
    }

    @ParameterizedTest                              //runs same test with multiple inputs
    @ValueSource(doubles = {0.01, 100.00, 9999.99})
    @DisplayName("Deposit: various valid amounts all increase balance")
    void deposit_variousValidAmounts_allSucceed(double amount) {
        double before = account.getBalance();
        account.deposit(amount);
        assertTrue(account.getBalance() > before);
    }

    //*******WITHDRAW TESTS

    @Test
    @DisplayName("Withdraw: valid amount decreases balance")
    void withdraw_validAmount_decreasesBalance() {
        account.withdraw(200.00);
        assertEquals(800.00, account.getBalance());
    }

    @Test
    @DisplayName("Withdraw: exact balance empties account")
    void withdraw_exactBalance_leavesZero() {
        account.withdraw(1000.00);
        assertEquals(0.00, account.getBalance());
    }

    @Test
    @DisplayName("Withdraw: overdraft throws exception")
    void withdraw_overdraft_throwsException() {
        assertThrows(IllegalStateException.class, () -> account.withdraw(1500.00));
    }

    @Test
    @DisplayName("Withdraw: negative amount throws exception")
    void withdraw_negativeAmount_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-50));
    }

    //*******CONSTRUCTOR TESTS

    @Test
    @DisplayName("Constructor: negative initial balance throws exception")
    void constructor_negativeBalance_throwsException() {
        assertThrows(IllegalArgumentException.class,
            () -> new BankAccount("Bob", -500.00));
    }

    @Test
    @DisplayName("Constructor: valid account sets owner correctly")
    void constructor_validInput_setsOwner() {
        assertEquals("Alice", account.getOwner());
    }

    //*******GROUPED TESTS

    @Nested                                         //groups related tests visually in reports
    @DisplayName("Balance boundary conditions")
    class BalanceBoundaryTests {

        @Test
        @DisplayName("Zero balance account can deposit")
        void zeroBalanceAccount_canDeposit() {
            BankAccount empty = new BankAccount("Bob", 0.00);
            empty.deposit(100.00);
            assertEquals(100.00, empty.getBalance());
        }

        @Test
        @DisplayName("Zero balance account cannot withdraw")
        void zeroBalanceAccount_cannotWithdraw() {
            BankAccount empty = new BankAccount("Bob", 0.00);
            assertThrows(IllegalStateException.class, () -> empty.withdraw(1.00));
        }
    }
}
