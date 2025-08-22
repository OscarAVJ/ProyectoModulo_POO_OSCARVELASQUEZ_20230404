package Oscar_Velasquez_20230404.Oscar_Velasquez_20230404;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProyectoModuloPooOscarVelasquez20230404Application.class);
	}

}
