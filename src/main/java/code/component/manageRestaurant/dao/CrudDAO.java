package code.component.manageRestaurant.dao;

import java.util.List;

public interface CrudDAO<T> {

   // use in controller

   void add(T t);

   List<T> getPageByParent(Object parentKey);

   void deleteById(Integer id);

}