package com.roman.credit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebLayerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void shouldReturnSuccessMessage() throws Exception {
        this.mockMvc.perform(get("/ssnNumber/856-45-6789/loanAmmount/25.5?annualIncome=100")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("You have being granted loan")))
                .andExpect(content().string(containsString("50")))
        ;
    }
    @Test
    public void shouldReturnScoreTooLowMessage() throws Exception {
        this.mockMvc.perform(get("/ssnNumber/856-45-6788/loanAmmount/25.5?annualIncome=100")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("You score is too low for loan")))

        ;
    }
    @Test
    public void shouldThrowErrorOnInvalidSSN() throws Exception {
        this.mockMvc.perform(get("/ssnNumber/5-6789/loanAmmount/25.5?annualIncome=29")).andDo(print()).andExpect(status().isUnprocessableEntity());
    }
}
