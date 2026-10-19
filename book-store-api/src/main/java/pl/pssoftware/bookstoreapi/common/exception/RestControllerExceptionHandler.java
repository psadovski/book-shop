package pl.pssoftware.bookstoreapi.common.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.pssoftware.bookstoreapi.security.exception.UserAlreadyExistsException;

import java.time.Instant;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RestControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleEntityNotFoundException(
            EntityNotFoundException ex,
            HttpServletRequest request) {
        return getApiError(request, ex.getMessage(), HttpStatus.NOT_FOUND, ErrorCode.ENTITY_NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleUserAlreadyExists(UserAlreadyExistsException exception,
                                            HttpServletRequest request) {
        return getApiError(request, exception.getMessage(), HttpStatus.CONFLICT, ErrorCode.USER_ALREADY_EXISTS);
    }

    private static ApiError getApiError(HttpServletRequest request, String ex, HttpStatus notFound, ErrorCode entityNotFound) {
        log.error("Exception for request {}: {}", request.getRequestURI(), ex);

        return ApiError.builder()
                .timestamp(Instant.now())
                .status(notFound.value())
                .error(notFound.getReasonPhrase())
                .message(ex)
                .path(request.getRequestURI())
                .errorCode(entityNotFound)
                .build();
    }
}
