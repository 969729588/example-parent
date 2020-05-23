package com.milepost.exampleSingleBoot.auth.service;

import com.milepost.api.util.RequestUtil;
import com.milepost.exampleSingleBoot.auth.entity.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020-05-22.
 */
@Service
public class LoginService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     *  Jwt getToken(@RequestHeader(value = "Authorization") String authorization,
             @RequestParam("grant_type") String grantType,
             @RequestParam("username") String username,
             @RequestParam("password") String password);

     * @param request
     * @param basicAuthorization
     * @param grantType
     * @param username
     * @param password
     * @return
     */
    public Jwt getToken(HttpServletRequest request, String basicAuthorization, String grantType, String username, String password) throws URISyntaxException {
        String authUrl = RequestUtil.getBasePath(request) + "/oauth/token?grant_type=" + grantType + "&username=" + username + "&password=" + password;
        URI url = new URI(authUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", /*"Basic " + */basicAuthorization);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<Jwt> responseEntity = restTemplate.exchange(url , HttpMethod.POST, requestEntity, Jwt.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        if(statusCode.value() == 200){
            return responseEntity.getBody();
        }else{
            return null;
        }
    }

}
