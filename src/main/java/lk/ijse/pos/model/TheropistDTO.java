package lk.ijse.pos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TheropistDTO {
    private String theropistId;

    private String name;

    private String contact;

    private String email;

    private String status;
}
