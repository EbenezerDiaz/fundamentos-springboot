package com.ebenezerdiaz.springboot.fundamentos;

import com.ebenezerdiaz.springboot.fundamentos.bean.MyBean;
import com.ebenezerdiaz.springboot.fundamentos.bean.MyBeanWithDependency;
import com.ebenezerdiaz.springboot.fundamentos.bean.MyBeanWithProperties;
import com.ebenezerdiaz.springboot.fundamentos.component.ComponentDependency;
import com.ebenezerdiaz.springboot.fundamentos.entity.UserApp;
import com.ebenezerdiaz.springboot.fundamentos.pojo.UserPojo;
import com.ebenezerdiaz.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  MyBean myBean, MyBeanWithDependency myBeanWithDependency,
								  MyBeanWithProperties myBeanWithProperties, UserPojo userPojo,
								  UserRepository userRepository){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUsersInDatabase();
		getInformationJpqlFromUser();
		getFindAndSort();
		getFindByName();
		getFindByNameAndEmail();
		getFindByNameLike();
		getFindByNameLikeOrderByIdDesc();
		getFindByBirthDateAndEmail();
	}

	private void getInformationJpqlFromUser(){

		LOGGER.info("Usuario con el metodo findByUserEmail " +
			userRepository.findByUserEmail("ebenezer@email.com")
					.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

	}

	private void getFindAndSort(){
		userRepository.findAndSort("User", Sort.by("id").ascending())
				.stream().forEach(LOGGER::info);
		;
	}

	private void getFindByName(){
		LOGGER.info("getFindByName ====================================");
		userRepository.findByName("Ebenezer").stream().forEach(LOGGER::info);
	}

	private void getFindByNameAndEmail(){
		LOGGER.info("Usuario con el metodo getFindByNameAndEmail " +
				userRepository.findByNameAndEmail("Talina", "talina@email.com")
					.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

	}

	private void getFindByNameLike(){
		LOGGER.info("getFindByName ====================================");
		userRepository.findByNameLike("User%").stream().forEach(LOGGER::info);
	}

	private void getFindByNameLikeOrderByIdDesc(){
		LOGGER.info("findByNameLikeOrderByIdDesc ====================================");
		userRepository.findByNameLikeOrderByIdDesc("User%").stream().forEach(LOGGER::info);
	}

	private void getFindByBirthDateAndEmail(){
		LOGGER.info("Usuario con el metodo getFindByBirthDateAndEmail ================ " +
				userRepository.getAllByBirthDateAndEmail(LocalDate.of(1980, 01, 3), "talina@email.com")
						.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

	}

	private void saveUsersInDatabase(){
		UserApp user1 = new UserApp("Ebenezer", "ebenezer@email.com", LocalDate.of(1980, 01, 1));
		UserApp user2 = new UserApp("Luis", "luis@email.com", LocalDate.of(1980, 01, 2));
		UserApp user3 = new UserApp("Talina", "talina@email.com", LocalDate.of(1980, 01, 3));
		UserApp user4 = new UserApp("Leonardo", "leonardo@email.com", LocalDate.of(1980, 01, 4));
		UserApp user5 = new UserApp("User5", "user5@email.com", LocalDate.of(1980, 01, 5));
		UserApp user6 = new UserApp("User6", "user6@email.com", LocalDate.of(1980, 01, 6));
		UserApp user7 = new UserApp("User7", "user7@email.com", LocalDate.of(1980, 01, 7));
		UserApp user8 = new UserApp("User8", "user8@email.com", LocalDate.of(1980, 01, 8));
		UserApp user9 = new UserApp("User9", "user9@email.com", LocalDate.of(1980, 01, 9));
		UserApp user10 = new UserApp("User10", "user10@email.com", LocalDate.of(1980, 01, 10));

		List<UserApp> users = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);

		users.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + " - " + userPojo.getPassword());
	}
}
