package code.component.manageRestaurant.domain;

import code.component.manageAccount.domain.AccountEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant", schema = "food_delivery")
public class RestaurantEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name ="id")
   private Integer id;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
   private List<MenuEntity> menus;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "seller_id")
   private AccountEntity seller;

//   @ManyToOne(fetch = FetchType.LAZY)
//   @JoinColumn(name = "address_id")
//   private AddressEntity address;
}