package sofysmol.metodclient; /**
 * Created by sofysmo on 08.10.16.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import sofysmol.metodclient.data.Fakultet;
import sofysmol.metodclient.dao.impl.FakultetJDBCTemplete;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BootApplication.class, args);
        FakultetJDBCTemplete rep = ctx.getBean(FakultetJDBCTemplete.class);//new FakultetJDBCTemplete();
        Fakultet f = rep.getFakultet("ФКТИ");
        System.out.println(f.getName());
    }

}