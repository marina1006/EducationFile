package web;

import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import web.model.User;

public class RestClient {

  static RestTemplate restTemplate = new RestTemplate();
  static String baseUrl = "http://94.198.50.185:7081/api/users";;


  public static void main(String[] args) {
    useExchangeMethodsOfRestTemplate();

  }

  private static void useExchangeMethodsOfRestTemplate() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Object> requestEntity = new HttpEntity<>(headers);



    User user = new User();
    user.setId(3L);
    user.setAge((byte) 23);
    user.setName("James");
    user.setLastName("Brown");

    user.setName("Thomas");
    user.setLastName("Shelby");
    requestEntity = new HttpEntity<>(user, headers);
    getListUserByExchangeMethod(requestEntity);
    addUserByExchangeMethod(requestEntity);
    updateUserByExchangeMethod(requestEntity);
    deleteUserByExchangeMethod(requestEntity);

  }

  private static void deleteUserByExchangeMethod(HttpEntity<Object> requestEntity) {
    ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl + "/3",
        HttpMethod.DELETE,
        requestEntity,
        String.class);
    HttpStatus statusCode = responseEntity.getStatusCode();
    System.out.println("status code - " + statusCode);
    String userDetails = responseEntity.getBody();
    System.out.println("response body - " + userDetails);
    HttpHeaders responseHeaders = responseEntity.getHeaders();
    System.out.println("response Headers - " + responseHeaders);
  }

  private static void updateUserByExchangeMethod(HttpEntity<Object> requestEntity) {
    ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl,
        HttpMethod.PUT,
        requestEntity,
        String.class);
    HttpStatus statusCode = responseEntity.getStatusCode();
    System.out.println("status code - " + statusCode);
    String userDetails = responseEntity.getBody();
    System.out.println("response body - " + userDetails);
    HttpHeaders responseHeaders = responseEntity.getHeaders();
    System.out.println("response Headers - " + responseHeaders);
  }

  private static void addUserByExchangeMethod(HttpEntity<Object> requestEntity) {
    ResponseEntity<User> responseEntity = restTemplate.exchange(baseUrl,
        HttpMethod.POST,
        requestEntity,
        User.class);
    HttpStatus statusCode = responseEntity.getStatusCode();
    System.out.println("status code - " + statusCode);
    User userDetails = responseEntity.getBody();
    System.out.println("response body - " + userDetails);
    HttpHeaders responseHeaders = responseEntity.getHeaders();
    System.out.println("response Headers - " + responseHeaders);
  }


  private static void getListUserByExchangeMethod(HttpEntity<Object> requestEntity) {
    ResponseEntity<List> responseEntity = restTemplate.exchange(baseUrl,
        HttpMethod.GET,
        requestEntity,
        List.class);
    HttpStatus statusCode = responseEntity.getStatusCode();
    System.out.println("status code - " + statusCode);
    List user = responseEntity.getBody();
    System.out.println("response body - " + user);
    HttpHeaders responseHeaders = responseEntity.getHeaders();
    System.out.println("response Headers - " + responseHeaders);

  }

}
