package sofysmol.metodclient; /**
 * Created by sofysmo on 08.10.16.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BootApplication.class, args);
    }

}