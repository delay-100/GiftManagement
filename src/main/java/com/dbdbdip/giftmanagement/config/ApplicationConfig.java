package com.dbdbdip.giftmanagement.config;


import com.dbdbdip.giftmanagement.repository.UsersRepository;
import com.dbdbdip.giftmanagement.repository.GiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final GiftRepository giftRepository;
    private final UsersRepository usersRepository;

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        return new HiddenHttpMethodFilter();
    }
}
