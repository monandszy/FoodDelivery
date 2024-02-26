package code.component.manageOrder.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_position", schema = "food_delivery")
public class OrderPositionEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "order_id")
   private OrderEntity order;

   @Column(name = "menu_position_id")
   private Integer menuPositionId;
}