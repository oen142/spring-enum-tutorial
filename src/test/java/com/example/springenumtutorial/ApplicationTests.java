package com.example.springenumtutorial;

import com.example.springenumtutorial.before.Commission;
import com.example.springenumtutorial.before.Contract;
import com.example.springenumtutorial.before.ContractRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApplicationTests {

    @Autowired
    private ContractRepository repository;

    @Test
    public void add() {
        Contract contract = new Contract(
                "우아한짐카",
                1.0,
                "percent",
                "round"
        );
        repository.save(contract);
        Contract saved = repository.findAll().get(0);
        assertThat(saved.getCommission()).isEqualTo(1.0);
    }

    @Test
    public void add_staticVariable() {
        Contract contract = new Contract(
                "우아한짐카",
                1.0,
                Commission.TYPE_PERCENT,
                Commission.CUTTING_ROUND
        );
        repository.save(contract);
        Contract saved = repository.findAll().get(0);
        assertThat(saved.getCommission()).isEqualTo(1.0);
    }
}
