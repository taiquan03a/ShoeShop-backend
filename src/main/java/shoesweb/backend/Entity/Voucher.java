package shoesweb.backend.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vouchers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String category;
    private Boolean type;
    private String code;
    private float discount;
    @Column(name = "max_discount")
    private float maxDiscount;
    @Column(name = "min_price")
    private float minDiscount;
    private int quantity;
    private String describe;
}
