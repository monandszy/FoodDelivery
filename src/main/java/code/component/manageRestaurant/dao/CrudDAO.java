package code.component.manageRestaurant.dao;

import java.util.List;

public interface CrudDAO<T> {

   // for use in controllers - relaxed architecture - skip service layer
   // simple repeatable crud - if more complex needed, create separate service

   void add(T t);

   List<T> getPageByParent(Object parentKey, Integer page);

   void deleteById(Integer id);

}