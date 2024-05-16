package hongik.hongikhospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HongikhospitalApplication {

	public static void main(String[] args) {


		Hello hello = new Hello();
		hello.setData("hello");
		String data = hello.getData();

		SpringApplication.run(HongikhospitalApplication.class, args);
	}

}
