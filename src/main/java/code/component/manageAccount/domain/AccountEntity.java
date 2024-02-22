package code.component.manageAccount.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {})
@ToString(of = {})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seller", schema = "food_delivery")
public class AccountEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "user_name")
   private String userName;

   @Column(name = "password")
   private String password;

   @Column(name = "active")
   private Boolean active;

   @ManyToMany(cascade = CascadeType.MERGE)
   @JoinTable(
       name = "account_role",
       joinColumns = @JoinColumn(name = "account_id"),
       inverseJoinColumns = @JoinColumn(name = "role_id")
   )
   private Set<RoleEntity> roles;
}