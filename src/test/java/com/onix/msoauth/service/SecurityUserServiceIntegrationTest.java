package com.onix.msoauth.service;

import com.onix.msoauth.entities.SecurityUser;
import com.onix.msoauth.services.SecurityUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SecurityUserServiceIntegrationTest {

    @Autowired
    private SecurityUserService securityUserService;

    @Test
    public void signup(){
        Optional<SecurityUser> securityUser = securityUserService.signup("lars","metallica",
                                                                         "Lars", "Ulrich");
        assert(!"metallica".equals(securityUser.get().getPassword()));
        System.out.println(securityUser.get().getPassword());
    }

}
