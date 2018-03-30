package com.ef.core.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Log implements Serializable {

    private Long id;

    private String ip;

    private Integer statusCode;

    private String userAgent;

    private String httpMethod;

    private LocalDateTime requestDate;

    public Log() { }

    private Log(Builder builder) {
        id = builder.id;
        ip = builder.ip;
        statusCode = builder.statusCode;
        userAgent = builder.userAgent;
        httpMethod = builder.httpMethod;
        requestDate = builder.requestDate;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", statusCode=" + statusCode +
                ", userAgent='" + userAgent + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", requestDate=" + requestDate +
                '}';
    }

    public static final class Builder {
        private Long id;
        private String ip;
        private String description;
        private Integer statusCode;
        private String userAgent;
        private String httpMethod;
        private LocalDateTime requestDate;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withIp(String val) {
            ip = val;
            return this;
        }

        public Builder withStatusCode(Integer val) {
            statusCode = val;
            return this;
        }

        public Builder withUserAgent(String val) {
            userAgent = val;
            return this;
        }

        public Builder withHttpMethod(String val) {
            httpMethod = val;
            return this;
        }

        public Builder withRequestDate(LocalDateTime val) {
            requestDate = val;
            return this;
        }

        public Log build() {
            return new Log(this);
        }
    }
}
