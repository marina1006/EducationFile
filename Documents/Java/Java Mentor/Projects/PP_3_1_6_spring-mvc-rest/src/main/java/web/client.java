package web;

import static org.springframework.http.HttpHeaders.COOKIE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.net.CookieHandler;
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
    useExchangeMethodsOfRestTemplate();
  }

    private static void useExchangeMethodsOfRestTemplate () throws JsonProcessingException {

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

      ResponseEntity<String> response = restTemplate.exchange(
          url, HttpMethod.GET, requestEntity, String.class);

      System.out.println(response.getBody());

      HttpHeaders headers2 = new HttpHeaders();
      headers2.setContentType(MediaType.APPLICATION_JSON);

      headers2.add("JSESSIONID",  "COOKIE");

      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
      User user = new User();

      user.setId(3L);
      user.setAge((byte) 23);
      user.setName("James");
      user.setLastName("Brown");

      String json = ow.writeValueAsString(user);
      JSONObject jsonObject = new JSONObject(user);

      HttpEntity<Object> requestEntity2 = new HttpEntity<>(jsonObject.toString(),headers2);
      ResponseEntity<String> response2 = restTemplate.exchange(
          url, HttpMethod.POST, requestEntity2, String.class);
      System.out.println(response2.getBody());


      user.setName("Thomas");
      user.setLastName("Shelby");
      jsonObject = new JSONObject(user);

      HttpHeaders headers3 = new HttpHeaders();
      headers3.setContentType(MediaType.APPLICATION_JSON);
      headers3.add("JSESSIONID", "COOKIE");

      HttpEntity<Object> requestEntity3 = new HttpEntity<>(jsonObject.toString(),headers3);
      ResponseEntity<String> response3 = restTemplate.exchange(
          url, HttpMethod.PUT, requestEntity3, String.class);


      System.out.println(response3.getBody());

      HttpHeaders headers4 = new HttpHeaders();
      headers4.setContentType(MediaType.APPLICATION_JSON);
      headers4.add("JSESSIONID", "COOKIE");

      HttpEntity<Object> requestEntity4 = new HttpEntity<>(headers4);
      ResponseEntity<String> response4 = restTemplate.exchange(
          url2, HttpMethod.DELETE, requestEntity4, String.class);

     ; // 5ebfeb


      System.out.println(response4.getBody());
    }

  }

