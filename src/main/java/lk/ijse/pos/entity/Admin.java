package lk.ijse.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @Column(name = "admin_id")
    private String adminId;

    @Column(length = 25, nullable = false)
    private String username;

    @Column(length = 25, nullable = false)
    private String password;




}
