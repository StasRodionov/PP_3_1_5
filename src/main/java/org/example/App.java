package org.example;

import org.example.entity.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
public class App {
    static final String URL = "http://94.198.50.185:7081/api/users";


    public static void main(String[] args) {

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Object> request = new HttpEntity<>(null,null);
        HttpEntity<String> response = new HttpEntity<>(null,null);
        String set_cookie = template.exchange(URL, HttpMethod.GET, request, String.class).getHeaders().getFirst(HttpHeaders.SET_COOKIE);

        System.out.println("Response: " + response.toString() + "\n");
        System.out.println("Set-Cookie: " + set_cookie + "\n");
        System.out.println("********* FINISH *******");

        headers.set(HttpHeaders.COOKIE, set_cookie);

        User user = new User(3L, "James", "Brown", (byte) 26);
        request = new HttpEntity<>(user, headers);
        System.out.println(request);

        response = template.exchange(URL, HttpMethod.POST, request, String.class);
        System.out.println(response.getBody());

        user = new User(3L, "Thomas", "Shelby", (byte) 26);
        request = new HttpEntity<>(user, headers);
        System.out.println(request);

        response = template.exchange(URL, HttpMethod.PUT, request, String.class);
        System.out.println(response.getBody());

        request = new HttpEntity<>(null, headers);
        response = template.exchange(URL + "/" + 3, HttpMethod.DELETE, request, String.class);
        System.out.println(response.getBody());
    }
}


