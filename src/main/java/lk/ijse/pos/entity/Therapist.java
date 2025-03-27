package lk.ijse.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "therapist")
public class Therapist {

    @Id
    @Column(name = "therapistId")
    private String therapistId;

    @Column(length = 50)
    private String name;

    @Column(length = 10)
    private String contact;

    @Column(length = 60)
    private String email;

    @Column(length = 10)
    private String status;
}
