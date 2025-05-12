package spring_boot.demo.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring_boot.demo.entry.UserEntry;
import spring_boot.demo.repository.UserEntryRepo;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserEntryRepo userEntryRepo;

    @Disabled
    @Test
    public void testFindByUserName() {
//        assertEquals(4 , 2 + 2);
        assertNotNull(userEntryRepo.findByUserName("demo"));
        UserEntry viki = userEntryRepo.findByUserName("demo");
        assertTrue(!viki.getJournalEntries().isEmpty());
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1, 1 , 2",
            "2 , 2 , 4",
            "3, 3 , 7"
    })
    public void test(int a, int b, int expected) {
        assertEquals(a + b, expected);
    }


    @Disabled
    @ParameterizedTest
    @CsvSource({
            "demo1",
            "demo2",
            "demo3",
            "demo4"
    })

    public void testUserName(String name) {
        assertNotNull(userEntryRepo.findByUserName(name));
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "demo1",
            "demo2",
            "demo3",
            "demo4"
    })
    public void testUserName2(String name){
        assertNotNull(userEntryRepo.findByUserName(name));
    }

}
