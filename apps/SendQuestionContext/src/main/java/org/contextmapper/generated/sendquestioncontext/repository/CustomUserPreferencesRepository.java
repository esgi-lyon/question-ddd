package org.contextmapper.generated.sendquestioncontext.repository;

import org.contextmapper.generated.sendquestioncontext.domain.UserPreferences;
import org.contextmapper.generated.sendquestioncontext.domain.UserPreferencesTagInfos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Spring Data JPA repository for the UserPreferences entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomUserPreferencesRepository extends JpaRepository<UserPreferences, Long> {
    List<UserPreferences> findAllByPreferencesContaining(Set<UserPreferencesTagInfos> preferences);
}
