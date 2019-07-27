package com.levent.flightapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightApiApplicationTests {

    @Autowired
    private CommandLineRunner commandLineRunner;

    @Test
    public void contextLoads() throws Exception {
        this.commandLineRunner.run("");
    }

}
