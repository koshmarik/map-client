package org.koshenkova;

import com.jayway.restassured.http.ContentType;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.koshenkova.view.MapPointView;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MapClientApplication.class)
@WebIntegrationTest(randomPort = true)
public class MapClientApplicationTests {

    @Test
    public void testFindAllPoints() {
        when().get("/map_point").then().
                statusCode(HttpStatus.OK.value()).
                body("size", equalTo(5));

        when().get("/map_point").then().
                assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void testFindById() {
        given().pathParam("id", 1).
                when().get("/map_point/{id}").then().
                body("id", equalTo(1)).
                body("shortName", equalTo("Google")).
                body("link", nullValue());
    }

    @Ignore
    @Test
    public void testAdd() {
        MapPointView mapPointView = new MapPointView();
        mapPointView.setLink("ya.ru");
        mapPointView.setLatitude(55.763638);
        mapPointView.setLongitude(37.565466);
        mapPointView.setShortName("Test");
        given().
                body(mapPointView).
                when().post("/map_point");
    }

}
