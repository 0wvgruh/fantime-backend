package com.fantime.fantime_backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "idol_group")
public class IdolGroup {
        @Id
        @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
        private Long id;
        private String name;
        @Column(name = "debut_date")
        private LocalDate debutDate;
        @Column(name = "agency")
        private String agency;
        @Column(name = "created_at")
        private LocalDateTime createdAt;
    }
