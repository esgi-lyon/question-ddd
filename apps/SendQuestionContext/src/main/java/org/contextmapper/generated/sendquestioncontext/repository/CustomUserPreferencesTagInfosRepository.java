package org.contextmapper.generated.sendquestioncontext.repository;

import org.contextmapper.generated.sendquestioncontext.domain.UserPreferencesTagInfos;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the UserPreferencesTagInfos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomUserPreferencesTagInfosRepository extends JpaRepository<UserPreferencesTagInfos, Long> {
    @NotNull
    @Override
    List<UserPreferencesTagInfos> findAllById(Iterable<Long> longs);
}
