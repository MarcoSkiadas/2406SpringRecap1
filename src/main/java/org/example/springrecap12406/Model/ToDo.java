package org.example.springrecap12406.Model;

import lombok.With;
import org.springframework.data.annotation.Id;

@With
public record ToDo(
        @Id
        String id,
        String description,
        Status status) {
}
