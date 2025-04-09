package br.com.productassistant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "v_category")
@Data
public class CategoryView {

    @Id
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "display_name")
    private String displayName;
}
