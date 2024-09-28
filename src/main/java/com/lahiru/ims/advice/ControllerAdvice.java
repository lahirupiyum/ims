package com.lahiru.ims.advice;

import com.lahiru.ims.common.dto.StandardReponse;
import com.lahiru.ims.exception.DataConflictException;
import com.lahiru.ims.exception.NotFoundException;
import com.lahiru.ims.utils.ResponseEntityManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardReponse<Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest request) {
        logError(exception, request);
        return ResponseEntityManager.badRequest(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardReponse<Object>> handleNotFound(NotFoundException notFoundException, WebRequest webRequest) {
        log.error(notFoundException.getMessage(), notFoundException);
        logError(notFoundException, webRequest);
        return ResponseEntityManager.notFound(notFoundException.getMessage());
    }

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<StandardReponse<Object>> handleConflict(DataConflictException dataConflictException, WebRequest webRequest) {
        logError(dataConflictException, webRequest);
        return ResponseEntityManager.conflict(dataConflictException.getMessage());
    }

    private void logError(Exception e, WebRequest request) {
        log.error("Error occurred in {}", request.getContextPath());
        log.error(e.getMessage());
    }
}
