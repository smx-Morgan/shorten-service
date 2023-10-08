package com.shorten.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "t_url_map", indexes = {@Index(columnList = "longUrl", unique = true),
@Index(columnList = "expireTime", unique = false)})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UrlMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String longUrl;

    private Instant expireTime;

    @CreationTimestamp
    private Instant creationTime;
}
