package kz.aibat.springboot.security.security.repository;

import kz.aibat.springboot.security.security.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
