package com.proost.project.socialnews.web.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class HelloResponseTest {

    @Test
    public void testLombok() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponse dto = new HelloResponse(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
