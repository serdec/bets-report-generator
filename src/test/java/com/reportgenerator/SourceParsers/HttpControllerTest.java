package com.reportgenerator.SourceParsers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reportgenerator.Objects.Bet;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class HttpControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void noBody() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void statusOk() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Bet b1 = Bet.createBet("Bet-1", 2135435, 2, "WinnerHorse", BigDecimal.valueOf(3.0), BigDecimal.valueOf(2.23), "GBP");
        Bet b2 = Bet.createBet("Bet-2", 2135435, 2, "WinnerHorse", BigDecimal.valueOf(3.0), BigDecimal.valueOf(2.23), "GBP");
        List<Bet> body = new ArrayList<>();
        body.add(b1);
        mvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
                .andExpect(status().isOk());
    }
}

