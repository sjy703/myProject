package com.jy73.basic.service;

import com.jy73.basic.dto.UserAccountDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @Test
    public void login() throws Exception {
        UserAccountDto dto = new UserAccountDto().builder().name("서진영")
                .password("wlsdud84711!").phoneNumber("010-9774-1491").userId("sjy703").build();
        loginService.singUp(dto);
        String token = loginService.login(dto);
        System.out.println(token);
        assertNotNull(token);
    }

    @Test
    public void singup() throws Exception {
        UserAccountDto dto = new UserAccountDto().builder().name("서진영")
                .password("wlsdud84711!").phoneNumber("010-9774-1491").userId("sjy703").build();
        loginService.singUp(dto);
    }
}