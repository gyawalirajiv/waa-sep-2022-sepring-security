package com.example.waasep2022sepringsecurity.aspects;

import com.example.waasep2022sepringsecurity.entity.User;
import com.example.waasep2022sepringsecurity.repository.OffensiveWordRepo;
import com.example.waasep2022sepringsecurity.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
@Transactional
public class WaaOffensiveWords {

    private final HttpServletRequest httpServletRequest;
    private final UserRepo userRepo;
    private final OffensiveWordRepo offensiveWordRepo;

    @Before("@annotation(com.example.waasep2022sepringsecurity.aspects.OffensiveFilter)")
    public void offensiveGuard(JoinPoint joinPoint) throws IOException {
        User user = getUser();
        UserOffensiveCount userOffensiveCount = getOffensiveCount(user);
        if(userOffensiveCount.getCount() == 5 && userOffensiveCount.getDateTime().isBefore(LocalDateTime.now())){
            userOffensiveCount.setCount(0);
        }

        String s = httpServletRequest.getHeader("word");
        if(s != null && s.contains("springing")){
            userOffensiveCount.setCount(userOffensiveCount.getCount() + 1);
            if(userOffensiveCount.getCount() == 5){
                userOffensiveCount.setDateTime(LocalDateTime.now().plus(10, ChronoUnit.SECONDS));
                throw new RuntimeException("Max Bad Words Requests Limit has been Reached. You need wait for " + userOffensiveCount.getDateTime() + " minutes.");
            }
        }
    }

    public User getUser(){
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal). getUsername();
        } else {
            username = principal. toString();
        }
        return userRepo.findByEmail(username);
    }

    public UserOffensiveCount getOffensiveCount(User user){
        Optional<UserOffensiveCount> userOffensiveCountOptional = offensiveWordRepo.findByUser(user);
        if(!userOffensiveCountOptional.isPresent()){
            UserOffensiveCount newCount = new UserOffensiveCount();
            newCount.setCount(0);
            newCount.setUser(user);
            offensiveWordRepo.save(newCount);
            return newCount;
        }
        return userOffensiveCountOptional.get();
    }

}
