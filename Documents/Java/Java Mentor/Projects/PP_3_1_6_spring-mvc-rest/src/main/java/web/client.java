package web;

import static org.springframework.http.HttpHeaders.COOKIE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.net.CookieHandler;
import java.util.List;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.client.RestTemplate;
import web.model.User;

public class client {
  static RestTemplate restTemplate = new RestTemplate();
  static String url = "http://94.198.50.185:7081/api/users";
  static String url2 = "http://94.198.50.185:7081/api/users/3";
  public static void main(String[] args) throws JsonProcessingException {
//    useExchangeMethodsOfRestTemplate();
        ForEntityManager forEntity = new ForEntityManager();
    forEntity.driverMethod();
  }

  }
 class ForEntityManager {

  private String baseUrl = "http://94.198.50.185:7081/api/users";

  RestTemplate restTemplate = new RestTemplate();

  public void driverMethod() {
    System.out.println("*********** forEntity() methods demo ***********");
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

    getListObject(requestEntity);
    headers.add("JSESSIONID", COOKIE);
    addUser(requestEntity);
    deleteUser(requestEntity);
    updateUser(requestEntity);
  }


  private void getListObject(HttpEntity<Object> requestEntity) {
    ResponseEntity<List> responseEntity = restTemplate.exchange(baseUrl,
        HttpMethod.GET,
        requestEntity,
        List.class);
    System.out.println("response body - " + responseEntity.getBody());

  }

  private void addUser(HttpEntity<Object> requestEntity) {
    ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl,
        HttpMethod.POST,
        requestEntity,
        String.class);

//    ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseUrl, user, String.class);

    System.out.println("response body - " + responseEntity.getBody());

  }

  private void deleteUser(HttpEntity<Object> requestEntity) {
    ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl + "/3",
        HttpMethod.DELETE,
        requestEntity,
        String.class);


    System.out.println("response body - " + responseEntity.getBody());
  }

  private void updateUser(HttpEntity<Object> requestEntity) {
    ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl,
        HttpMethod.PUT,
        requestEntity,
        String.class);

    System.out.println("response body - " + responseEntity.getBody());
  }
}
