package org.beny.stock.repository;

import org.beny.stock.model.News;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends BaseRepository<News> {

    @EntityGraph(attributePaths = "comments")
    Optional<News> findById(Long id);

}
