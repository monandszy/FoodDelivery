package code.component.manageRestaurant.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = {})
@ToString(of = {})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu", schema = "food_delivery")
public class MenuEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name ="id")
   private Integer id;

   @Column(name ="menu_type")
   private Menu.MenuType menuType;

   @OneToMany()
   private List<MenuPositionEntity> positions;

   @ManyToOne()
   private RestaurantEntity restaurant;
}