package com.reportgenerator.SourceParsers;

import com.reportgenerator.Objects.Bet;
import com.reportgenerator.Reports.ConfigurationHandler;
import com.reportgenerator.Reports.ReportsCreator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class HttpController {

    ConfigurationHandler configurationHandler;

    @PostConstruct
    public void init() {
        configurationHandler = ConfigurationHandler.getInstance();
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String index(@RequestBody List<Bet> bets) {

        ReportsCreator reportsCreator = ReportsCreator.createReportsCreator();

        reportsCreator.processBets(bets);

        return "{}";
    }
}
