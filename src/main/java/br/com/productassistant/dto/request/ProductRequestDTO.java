package br.com.productassistant.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class ProductRequestDTO {

    @NotBlank
    @Size(max = 50)
    String name;

    @NotBlank
    @Size(max = 250)
    String description;

    @NotNull
    Long categoryId;

    @NotNull
    @Size(max = 255)
    String categoryDisplayName;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    BigDecimal price;

    @NotNull
    Boolean active;

}
