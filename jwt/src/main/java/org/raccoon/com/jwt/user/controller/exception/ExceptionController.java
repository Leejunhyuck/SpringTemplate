package org.raccoon.com.jwt.user.controller.exception;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value ="/exception")
public class ExceptionController {

    // @GetMapping(value = "/entrypoint")
    // public CommonResult entrypointException() {
    //     throw new CAuthenticationEntryPointException();
    // }

    // @GetMapping(value = "/accessdenied")
    // public CommonResult accessdeniedException() {
    //     throw new AccessDeniedException("");
    // }
    

}