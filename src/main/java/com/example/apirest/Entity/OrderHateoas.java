package com.example.apirest.Entity;



import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Entity
@Table(name = "customer_order")
public class OrderHateoas extends RepresentationModel<OrderHateoas> {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Status status;

    public OrderHateoas() {
    }

    public OrderHateoas(String description, Status status) {
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderHateoas{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderHateoas that)) return false;
        return getId().equals(that.getId()) && Objects.equals(getDescription(), that.getDescription()) && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getStatus());
    }
}
