package zerobase.tablemate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TableMateApplication {

    public static void main(String[] args) {
        SpringApplication.run(TableMateApplication.class, args);
    }

}
