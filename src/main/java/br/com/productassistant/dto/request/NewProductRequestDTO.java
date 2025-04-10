package br.com.productassistant.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NewProductRequestDTO extends ProductRequestDTO{

    @NotBlank
    @Size(max = 50)
    String createdBy;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime createdAt;

}
