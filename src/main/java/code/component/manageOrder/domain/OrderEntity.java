package code.component.manageOrder.domain;

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
@Table(name = "client_order", schema = "food_delivery")
public class OrderEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "client_id")
   private AccountEntity client;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "menuPosition")
   private List<OrderPositionEntity> menuPositions;
}