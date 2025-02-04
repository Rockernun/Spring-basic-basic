package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        System.out.println("statefulService1 = " + statefulService1);
        System.out.println("statefulService2 = " + statefulService2);

        // Thread A : 사용자 A가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);

        // Thread B : 사용자 A가 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        System.out.println("price = " + userAPrice);
        System.out.println("price = " + userBPrice);

        // Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}