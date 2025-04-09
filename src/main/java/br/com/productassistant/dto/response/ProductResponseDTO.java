package br.com.productassistant.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class ProductResponseDTO {

    @NotNull
    Long id;

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

    @NotBlank
    @Size(max = 50)
    String createdBy;

    @Size(max = 50)
    String lastUpdatedBy;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime lastUpdatedAt;

}
