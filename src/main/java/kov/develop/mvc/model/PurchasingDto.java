package kov.develop.mvc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "id")
@EqualsAndHashCode(exclude = "id")
@NamedNativeQuery(name=PurchasingDto.GET_ALL, query="SELECT p.id, u.username, g.name AS goodname, p.date, p.price, p.quantity FROM  purchasings p " +
        "LEFT JOIN users u ON p.user_id = u.id LEFT JOIN goods g ON p.good_id = g.id", resultClass = PurchasingDto.class)

public class PurchasingDto {

    public static final String GET_ALL = "PurchasingDto.getAll";

    @Id
    private Integer id;

    @JsonProperty("username")
    @Column(name = "username")
    private String username;

    @JsonProperty("goodname")
    @Column(name = "goodname")
    private String goodname;

    @JsonProperty("date")
    @Column(name = "date")
    private LocalDate date;

    @JsonProperty("price")
    @Column(name = "price")
    private Double price;

    @JsonProperty("quantity")
    @Column(name = "quantity")
    private Integer quantity;

}
