package kov.develop.mvc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "purchasings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "id")
@EqualsAndHashCode(exclude = "id")
public class Purchasing implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty()
    private Integer id;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    @NotNull(message = "User's id must be not empty!")
    private Integer user_id;

    @Column(name = "good_id")
    @JsonProperty("good_id")
    @NotNull(message = "Good's id must be not empty!")
    private Integer good_id;

    @Column(name = "date")
    @JsonProperty("date")
    @NotNull(message = "Date must be not empty!")
    private LocalDate date;

    @Column(name = "price")
    @NotNull(message = "Good's price must be not empty!")
    @JsonProperty("price")
    private Double price;

    @Column(name = "quantity")
    @JsonProperty("quantity")
    private Integer quantity;

}
