package org.beny.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import static org.beny.stock.exception.StockError.ITEM_NOT_EXISTS;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {

    default T findOneById(Long id) {
        return this.findById(id)
                .orElseThrow(ITEM_NOT_EXISTS::exception);
    }

}
