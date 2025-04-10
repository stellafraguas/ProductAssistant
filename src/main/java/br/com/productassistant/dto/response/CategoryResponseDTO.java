package br.com.productassistant.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CategoryResponseDTO {

    @NotNull
    Long categoryId;

    @NotNull
    @Size(max = 255)
    String categoryDisplayName;

}
