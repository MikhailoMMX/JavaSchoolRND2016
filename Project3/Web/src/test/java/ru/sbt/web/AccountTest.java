package ru.sbt.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.sbt.data.Account;
import ru.sbt.data.Client;
import ru.sbt.hibernateutil.DataController;
import ru.sbt.hibernateutil.IDataController;

import java.math.BigDecimal;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
    private MockMvc mockMvc;

    @Mock
    IDataController dataController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new DataController()).build();
    }

    @Test
    public void testGetAccount() throws Exception {
//        Client client = new Client(1L, "Test");
//        Account account = new Account(1L, new BigDecimal(0), "0000", client);
//        Mockito.when(dataController.getClientByName("Test")).thenReturn(client);
//        ArrayList<Account> accList = new ArrayList<>();
//        accList.add(account);
//        Mockito.when(dataController.getAccounts(client)).thenReturn(accList);

//        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/{name}", "Test").accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.xpath("/List/item/accNum").string("0000"));

//        mockMvc.perform(MockMvcRequestBuilders.get("/world"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
