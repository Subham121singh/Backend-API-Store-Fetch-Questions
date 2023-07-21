package com.samta.assignment.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @Column(name = "category_id")
    private Long id;
    private String title;
    private String created_at;
    private String updated_at;
    @Column(name = "clues_count")
    private int clues_count;
}
