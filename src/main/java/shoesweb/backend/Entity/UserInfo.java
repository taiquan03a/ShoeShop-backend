package shoesweb.backend.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "username")
    private String name;
    private String email;
    private String password;
    private String phone;
    private String birthday;
    @Column(name = "full_name")
    private String fullName;
    private String avatar;
    private int status;
    @Column(name = "role_id")
    private int roleId;
    private String roles;
}
