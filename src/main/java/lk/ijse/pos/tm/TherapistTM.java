package lk.ijse.pos.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class TherapistTM {
    private String theropistId;

    private String name;

    private String contact;

    private String email;

    private String status;
}
