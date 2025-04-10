package br.com.productassistant.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class UpdateProductRequestDTO extends ProductRequestDTO{

    @Size(max = 50)
    String lastUpdatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime lastUpdatedAt;

}
