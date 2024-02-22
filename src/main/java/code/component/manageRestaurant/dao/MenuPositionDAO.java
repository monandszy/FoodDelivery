package code.component.manageRestaurant.dao;

import code.component.manageRestaurant.domain.MenuPosition;

import java.util.List;

public interface MenuPositionDAO extends CrudDAO<MenuPosition> {
   List<MenuPosition> getMenuPositions(Integer menuId);
}