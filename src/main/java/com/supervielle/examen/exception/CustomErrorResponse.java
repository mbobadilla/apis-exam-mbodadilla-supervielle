package com.supervielle.examen.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Schema(
        name = "Error",
        description = "System Error"
)
public class CustomErrorResponse {
    @Schema(
            name = "code",
            description = "error code",
            example = "INTERNAL SERVER ERROR"
    )
    private String code;
    @Schema(
            name = "causes",
            description = "error causes description",
            example = "Error tecnico. Por favor contacte el administrador"
    )
    private String causes;
    @Schema(
            name = "status",
            description = "http status error code",
            example = "500"
    )
    private Integer status;
    @Schema(
            name = "timestamp",
            description = "date and hour",
            example = "2021-01-08 06:20:58"
    )
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd hh:mm:ss"
    )
    private LocalDateTime timestamp;
    @Schema(
            name = "traceId",
            description = "trace identification (X-B3-TraceId http header)",
            example = "1234567890abcdee"
    )
    private String traceId;
    @Schema(
            name = "errores",
            description = "detail when there are many errors for notification"
    )
    private List<String> errors = null;

    public CustomErrorResponse(final String traceId, final String code, final String causes, List<String> errors) {
        this.code = code;
        this.causes = causes;
        this.traceId = traceId;
        if (errors != null) {
            this.errors = errors;
        } else {
            this.errors = Collections.emptyList();
        }

    }

    public String getCode() {
        return this.code;
    }

    public String getCauses() {
        return this.causes;
    }

    public Integer getStatus() {
        return this.status;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setCauses(final String causes) {
        this.causes = causes;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd hh:mm:ss"
    )
    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setTraceId(final String traceId) {
        this.traceId = traceId;
    }

    public void setErrors(final List<String> errors) {
        this.errors = errors;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CustomErrorResponse)) {
            return false;
        } else {
            CustomErrorResponse other = (CustomErrorResponse)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                Object this$code = this.getCode();
                Object other$code = other.getCode();
                if (this$code == null) {
                    if (other$code != null) {
                        return false;
                    }
                } else if (!this$code.equals(other$code)) {
                    return false;
                }

                Object this$causes = this.getCauses();
                Object other$causes = other.getCauses();
                if (this$causes == null) {
                    if (other$causes != null) {
                        return false;
                    }
                } else if (!this$causes.equals(other$causes)) {
                    return false;
                }

                label62: {
                    Object this$timestamp = this.getTimestamp();
                    Object other$timestamp = other.getTimestamp();
                    if (this$timestamp == null) {
                        if (other$timestamp == null) {
                            break label62;
                        }
                    } else if (this$timestamp.equals(other$timestamp)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$traceId = this.getTraceId();
                    Object other$traceId = other.getTraceId();
                    if (this$traceId == null) {
                        if (other$traceId == null) {
                            break label55;
                        }
                    } else if (this$traceId.equals(other$traceId)) {
                        break label55;
                    }

                    return false;
                }

                Object this$errors = this.getErrors();
                Object other$errors = other.getErrors();
                if (this$errors == null) {
                    if (other$errors != null) {
                        return false;
                    }
                } else if (!this$errors.equals(other$errors)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CustomErrorResponse;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $status = this.getStatus();
         result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $causes = this.getCauses();
        result = result * 59 + ($causes == null ? 43 : $causes.hashCode());
        Object $timestamp = this.getTimestamp();
        result = result * 59 + ($timestamp == null ? 43 : $timestamp.hashCode());
        Object $traceId = this.getTraceId();
        result = result * 59 + ($traceId == null ? 43 : $traceId.hashCode());
        Object $errors = this.getErrors();
        result = result * 59 + ($errors == null ? 43 : $errors.hashCode());
        return result;
    }

    public String toString() {
        return "CustomErrorResponse(code=" + this.getCode() + ", causes=" + this.getCauses() + ", status=" + this.getStatus() + ", timestamp=" + this.getTimestamp() + ", traceId=" + this.getTraceId() + ", errors=" + this.getErrors() + ")";
    }
}
