package com.waracle.cakemgr.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.waracle.cakemgr.CakeManagerApplication;
import com.waracle.cakemgr.entity.CakeEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CakeManagerApplication.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CakeServiceTest {

	@Autowired
	private WebApplicationContext webAppContext;

	@Autowired
	CakeService cakeService;

	private MockMvc mockMvc;

	@LocalServerPort
	private int port;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@Test
	public void findOneCakeTest() {
		CakeEntity entity = cakeService.findById(1);
		assertThat(entity.getTitle()).isEqualTo("Lemon cheesecake");
		assertThat(entity.getDesc()).isEqualTo("A cheesecake made of lemon");
		assertThat(entity.getImage()).isEqualTo(
				"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg");
	}

	@Test
	public void createCakeTest() {
		CakeEntity cakeEntity = new CakeEntity("Strawberry brown", "brown cake", "https://testcake.json");
		CakeEntity entity = cakeService.createNewCake(cakeEntity);
		cakeService.findById(21);
		assertThat(entity.getTitle()).isEqualTo("Strawberry brown");
		assertThat(entity.getDesc()).isEqualTo("brown cake");
		assertThat(entity.getImage()).isEqualTo("https://testcake.json");
		List<CakeEntity> empList = (List<CakeEntity>) cakeService.findAll();
		assertThat(empList.size()).isEqualTo(21);
	}

	@Test
	public void getAllCakesTest() throws JSONException {
		List<CakeEntity> empList = (List<CakeEntity>) cakeService.findAllCakesFromCjonFile();
		assertThat(empList.size()).isEqualTo(20);
		Gson gson = new Gson();
		String json = gson.toJson(empList);
		System.out.println(json);
	}

	private String jasonValues() {
		return "\"[ {\r\n" + "        \"employeeId\": 1,\r\n" + "        \"title\": \"Lemon cheesecake\",\r\n"
				+ "        \"desc\": \"A cheesecake made of lemon\",\r\n"
				+ "        \"image\": \"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 2,\r\n"
				+ "        \"title\": \"victoria sponge\",\r\n" + "        \"desc\": \"sponge with jam\",\r\n"
				+ "        \"image\": \"http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 3,\r\n"
				+ "        \"title\": \"Carrot cake\",\r\n" + "        \"desc\": \"Bugs bunnys favourite\",\r\n"
				+ "        \"image\": \"http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 4,\r\n"
				+ "        \"title\": \"Banana cake\",\r\n" + "        \"desc\": \"Donkey kongs favourite\",\r\n"
				+ "        \"image\": \"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 5,\r\n"
				+ "        \"title\": \"Birthday cake\",\r\n" + "        \"desc\": \"a yearly treat\",\r\n"
				+ "        \"image\": \"http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 6,\r\n"
				+ "        \"title\": \"Lemon cheesecake\",\r\n"
				+ "        \"desc\": \"A cheesecake made of lemon\",\r\n"
				+ "        \"image\": \"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 7,\r\n"
				+ "        \"title\": \"victoria sponge\",\r\n" + "        \"desc\": \"sponge with jam\",\r\n"
				+ "        \"image\": \"http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 8,\r\n"
				+ "        \"title\": \"Carrot cake\",\r\n" + "        \"desc\": \"Bugs bunnys favourite\",\r\n"
				+ "        \"image\": \"http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 9,\r\n"
				+ "        \"title\": \"Banana cake\",\r\n" + "        \"desc\": \"Donkey kongs favourite\",\r\n"
				+ "        \"image\": \"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 10,\r\n"
				+ "        \"title\": \"Birthday cake\",\r\n" + "        \"desc\": \"a yearly treat\",\r\n"
				+ "        \"image\": \"http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 11,\r\n"
				+ "        \"title\": \"Lemon cheesecake\",\r\n"
				+ "        \"desc\": \"A cheesecake made of lemon\",\r\n"
				+ "        \"image\": \"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 12,\r\n"
				+ "        \"title\": \"victoria sponge\",\r\n" + "        \"desc\": \"sponge with jam\",\r\n"
				+ "        \"image\": \"http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 13,\r\n"
				+ "        \"title\": \"Carrot cake\",\r\n" + "        \"desc\": \"Bugs bunnys favourite\",\r\n"
				+ "        \"image\": \"http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 14,\r\n"
				+ "        \"title\": \"Banana cake\",\r\n" + "        \"desc\": \"Donkey kongs favourite\",\r\n"
				+ "        \"image\": \"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 15,\r\n"
				+ "        \"title\": \"Birthday cake\",\r\n" + "        \"desc\": \"a yearly treat\",\r\n"
				+ "        \"image\": \"http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 16,\r\n"
				+ "        \"title\": \"Lemon cheesecake\",\r\n"
				+ "        \"desc\": \"A cheesecake made of lemon\",\r\n"
				+ "        \"image\": \"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 17,\r\n"
				+ "        \"title\": \"victoria sponge\",\r\n" + "        \"desc\": \"sponge with jam\",\r\n"
				+ "        \"image\": \"http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 18,\r\n"
				+ "        \"title\": \"Carrot cake\",\r\n" + "        \"desc\": \"Bugs bunnys favourite\",\r\n"
				+ "        \"image\": \"http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 19,\r\n"
				+ "        \"title\": \"Banana cake\",\r\n" + "        \"desc\": \"Donkey kongs favourite\",\r\n"
				+ "        \"image\": \"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 20,\r\n"
				+ "        \"title\": \"Birthday cake\",\r\n" + "        \"desc\": \"a yearly treat\",\r\n"
				+ "        \"image\": \"http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 21,\r\n"
				+ "        \"title\": \"Lemon sweet\",\r\n" + "        \"desc\": \"A cheesecake made of lemon\",\r\n"
				+ "        \"image\": \"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 22,\r\n"
				+ "        \"title\": \"Lemon sweet\",\r\n" + "        \"desc\": \"A cheesecake made of lemon\",\r\n"
				+ "        \"image\": \"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"employeeId\": 23,\r\n"
				+ "        \"title\": \"Lemon sweet\",\r\n" + "        \"desc\": \"A cheesecake made of lemon\",\r\n"
				+ "        \"image\": \"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"\r\n"
				+ "    }\r\n" + "]\"";

	}

}
