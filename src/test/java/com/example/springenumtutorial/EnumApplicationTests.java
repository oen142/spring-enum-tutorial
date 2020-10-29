package com.example.springenumtutorial;

import com.example.springenumtutorial.after.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class EnumApplicationTests {

    @Autowired
    private EnumContractRepository enumContractRepository;

    @Test
    public void add() {
        enumContractRepository.save(new EnumContract(
                "우아한짐카",
                1.0,
                EnumContract.CommissionType.MONEY,
                EnumContract.CommissionCutting.ROUND));

        EnumContract saved = enumContractRepository.findById(1L).orElse(null);

        assertThat(saved.getCommissionType()).isEqualTo(EnumContract.CommissionType.MONEY);
        assertThat(saved.getCommissionCutting()).isEqualTo(EnumContract.CommissionCutting.ROUND);
    }


    @Test
    public void EnumModel타입확인() {
        List<EnumModel> enumModels = new ArrayList<>();
        enumModels.add(EnumContract.CommissionType.MONEY);
        enumModels.add(EnumContract.CommissionCutting.CEIL);

        assertThat(enumModels.get(0).getValue()).isEqualTo("금액");
        assertThat(enumModels.get(1).getValue()).isEqualTo("올림");
    }

    @Autowired
    private EnumMapper enumMapper;

    @Test
    public void get() {
        String key = "commissionType";

        // Controller에 key를 던져 값을 가져왔다고 가정
        Map<String, List<EnumValue>> controllerResult = enumMapper.get(key);

        // JS에서 Controller에서 받은 값에서 원하는 enum type을 가져왔다고 가정
        List<EnumValue> viewResult = controllerResult.get(key);

        EnumValue percent = viewResult.get(0);

        assertThat(percent.getKey()).isEqualTo("PERCENT");
        assertThat(percent.getValue()).isEqualTo("퍼센트");
    }

    @Test
    public void EnumModel() {

        List<EnumModel> enumModels = Arrays
                .stream(EnumContract.CommissionType.class.getEnumConstants())
                .collect(Collectors.toList());

        assertThat(enumModels.get(0).getKey() ).isEqualTo("PERCENT");
    }

}
