package br.com.productassistant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 250, nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_category"))
    private Category category;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;

}
