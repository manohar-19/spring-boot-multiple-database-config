package com.manu.CyberArkApi.controller;

import com.manu.CyberArkApi.model.CredsModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseApi {
    @GetMapping("/getCreds/{dbName}")
    public ResponseEntity<CredsModel> getCreds(
            @PathVariable String dbName,
            @RequestHeader(value = "Authorization") String token
    ) throws RuntimeException {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (token.isEmpty() || dbName.isEmpty()) {
            throw new RuntimeException("token or database name is empty");
        } else {
            if (token.equals("SKJHASKJDHAKJSHDKGDASKJHDKSA")) {
                if (dbName.equals("first")) {
                    httpHeaders.add("Authorization", token);
                    return new ResponseEntity<>(CredsModel.builder()
                            .username("root")
                            .password("1234")
                            .build(), httpHeaders, HttpStatus.OK);
                } else {
                    httpHeaders.add("Authorization", token);
                    return new ResponseEntity<>(
                            CredsModel.builder()
                                    .username("springstudent")
                                    .password("springstudent")
                                    .build(), httpHeaders, HttpStatus.OK
                    );
                }
            } else {
                throw new RuntimeException("token is not valid");
            }

        }
    }
}
