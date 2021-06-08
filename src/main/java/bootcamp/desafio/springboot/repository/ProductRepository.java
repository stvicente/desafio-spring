package bootcamp.desafio.springboot.repository;

import bootcamp.desafio.springboot.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
