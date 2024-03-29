package com.rancard.service;

import com.rancard.exception.TransactionNotFoundException;
import com.rancard.model.Transaction;
import com.rancard.model.User;
import com.rancard.model.dto.transaction.CreateTransactionDto;
import com.rancard.model.dto.transaction.UpdateTransactionDto;
import com.rancard.repository.TransactionRepo;
import com.rancard.repository.UserRepo;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepo transactionRepo;

    @Mock
    private UserService userService;
    @InjectMocks
    private TransactionServiceImpl underTest;

    PodamFactoryImpl factory = new PodamFactoryImpl();

    User factoryUser ;
    Transaction factoryTransaction;
    @BeforeEach
    void setup(){

        factoryUser = factory.manufacturePojoWithFullData(User.class);
        factoryTransaction = factory.manufacturePojoWithFullData(Transaction.class);
    }

    @Test
    void create() {
        CreateTransactionDto createTransactionDto = factory.manufacturePojo(CreateTransactionDto.class);
        when(userService.getById(anyLong())).thenReturn(factoryUser);
        when(transactionRepo.save(any())).thenReturn(factoryTransaction);

        Transaction expected = underTest.create(createTransactionDto);

        verify(userService,times(2)).getById(anyLong());
        verify(transactionRepo,times(1)).save(any());
        assertEquals(expected.getSender(),factoryUser);
    }

    @Test
    void throwExceptionWhenSenderOrReceiverDoesNotExist(){
        CreateTransactionDto createTransactionDto = factory.manufacturePojo(CreateTransactionDto.class);

        doThrow(ServiceException.class).when(userService).getById(anyLong());

        assertThrows(ServiceException.class,()-> underTest.create(createTransactionDto));
    }

    @Test
    void delete() {
        when(transactionRepo.findById(any())).thenReturn(Optional.ofNullable(factoryTransaction));
        doNothing().when(transactionRepo).deleteById(anyLong());

        String expected = underTest.delete(1L);

        assertEquals("Deleted", expected);
    }

    @Test
    void update() {
        //given
        when(transactionRepo.findById(anyLong())).thenReturn(Optional.ofNullable(factoryTransaction));
        UpdateTransactionDto updateTransactionDto = factory.manufacturePojoWithFullData(UpdateTransactionDto.class);
        updateTransactionDto.setAmount(Optional.of(400.0));
        when(userService.getById(anyLong())).thenReturn(factoryUser);
        factoryTransaction.setSender(factoryUser);
        factoryTransaction.setAmount(updateTransactionDto.getAmount().get());

        when(transactionRepo.save(any())).thenReturn(factoryTransaction);

        //given
        Transaction expected = underTest.update(updateTransactionDto);

        //then
        verify(transactionRepo,times(1)).save(any());
        verify(transactionRepo,times(1)).findById(anyLong());
        assertEquals(expected.getAmount(),400.0,"The updated amount of the transaction should be 400");
    }

    @Test
    void list() {
       //given
        List<Transaction> transactions = IntStream.range(0, 5)
                .mapToObj(i -> factory.manufacturePojo(Transaction.class))
                .collect(Collectors.toList());
        Page<Transaction> transactionPage = new PageImpl<>(transactions);
        Pageable pageable = PageRequest.of(0,  10); // Example pageable object
        when(transactionRepo.findAll(pageable)).thenReturn(transactionPage);

        //when
        List<Transaction> result = underTest.list(pageable);

        //then
        verify(transactionRepo,times(1)).findAll(pageable);
        assertEquals(5, result.size(), "Expected five transactions in the list");
        assertEquals(transactions.get(0), result.get(0), "Expected the first transaction to match");
        assertEquals(transactions.get(1), result.get(1), "Expected the second transaction to match");

    }

    @Test
    void getById() {

        when(transactionRepo.findById(any())).thenReturn(Optional.of(factoryTransaction));

        Transaction expected= underTest.getById(factoryTransaction.getId());

        verify(transactionRepo,times(1)).findById(anyLong());
        assertEquals(expected,factoryTransaction);
    }

    @Test
    void throwExceptionWhenTransactionDoesNotExist(){

        when(transactionRepo.findById(anyLong())).thenThrow(new TransactionNotFoundException(factoryTransaction.getId()));

        assertThrows(TransactionNotFoundException.class,()-> underTest.getById(factoryTransaction.getId()));
    }
}