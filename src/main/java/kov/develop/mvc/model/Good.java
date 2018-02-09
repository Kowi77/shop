package kov.develop.mvc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "goods")
@Data
@NoArgsConstructor
@ToString(exclude = "id")
@EqualsAndHashCode(exclude = "id")
public class Good implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty()
    private Integer id;

    @Column(name = "name")
    @NotBlank(message = "Good's name must be not empty!")
    @Size(max = 60, message = "Good's name must be 60 charts max!")
    @JsonProperty("name")
    private String name;

    @Column(name = "description")
    @Size(max = 60, message = "Description must be 60 chart max!")
    @JsonProperty("description")
    private String description;

    @Column(name = "price")
    @NotNull(message = "Good's price must be not empty!")
    @JsonProperty("price")
    private Double price;

    @Column(name = "quantity")
    @JsonProperty("quantity")
    private Integer quantity;

}
