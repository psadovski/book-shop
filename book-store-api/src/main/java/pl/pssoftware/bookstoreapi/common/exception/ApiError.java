package pl.pssoftware.bookstoreapi.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private ErrorCode errorCode;
}
