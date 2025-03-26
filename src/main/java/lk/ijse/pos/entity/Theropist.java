package lk.ijse.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "theropist")
public class Theropist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String theropistId;

    private String name;

    private String contact;

    private String email;

    private String status;
}
