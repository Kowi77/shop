package kov.develop.mvc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import net.sf.ehcache.CacheManager;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "goods")
@Cacheable
@Entity
@Table(name = "goods")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @Min(0)
    @JsonProperty("price")
    private Double price;

    @Column(name = "quantity")
    @JsonProperty("quantity")
    @Min(0)
    private Integer quantity;

}
