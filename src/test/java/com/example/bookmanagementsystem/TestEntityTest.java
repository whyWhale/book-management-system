package com.example.bookmanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEntityTest {

    @Autowired
    TestEntityRepository repository;

    @Test
    @DisplayName("데모 엔티티 도메인을 저장한다.")
    void testInsert() {
        //given
        TestEntity savingEntity = new TestEntity("testEntityName");
        //when
        TestEntity savedTestEntity = repository.save(savingEntity);
        //then
        assertThat(savedTestEntity).isNotNull();
        assertThat(savedTestEntity).isEqualTo(savingEntity);
    }
}
