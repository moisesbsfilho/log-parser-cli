package com.ef.core.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class ThresholdSearch implements Serializable{

    private Long id;

    private Integer thresholdActual;

    private String ip;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String comment;

    private ThresholdSearch(Builder builder) {
        id = builder.id;
        thresholdActual = builder.thresholdActual;
        ip = builder.ip;
        startDate = builder.startDate;
        endDate = builder.endDate;
        comment = builder.comment;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public Integer getThresholdActual() {
        return thresholdActual;
    }

    public String getIp() {
        return ip;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThresholdSearch that = (ThresholdSearch) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(thresholdActual, that.thresholdActual) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, thresholdActual, ip, startDate, endDate, comment);
    }


    public static final class Builder {
        private Long id;
        private Integer thresholdActual;
        private String ip;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String comment;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withThresholdActual(Integer val) {
            thresholdActual = val;
            return this;
        }

        public Builder withIp(String val) {
            ip = val;
            return this;
        }

        public Builder withStartDate(LocalDateTime val) {
            startDate = val;
            return this;
        }

        public Builder withEndDate(LocalDateTime val) {
            endDate = val;
            return this;
        }

        public Builder withComment(String val) {
            comment = val;
            return this;
        }

        public ThresholdSearch build() {
            return new ThresholdSearch(this);
        }
    }
}
