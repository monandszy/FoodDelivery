package code.component.manageOrder.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "client_order", schema = "food_delivery")
public class OrderEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "client_code")
   private Integer clientCode;

   @OneToMany
   private List<OrderPositionEntity> menuPositions;
}