package lk.ijse.pos.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserTM {
    private String userId;
    private String username;
    private String password;

}
