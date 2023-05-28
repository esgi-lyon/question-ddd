package org.contextmapper.generated.sendquestioncontext.repository;

import org.contextmapper.generated.sendquestioncontext.domain.UserPreferences;
import org.contextmapper.generated.sendquestioncontext.domain.UserPreferencesTagInfos;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Spring Data JPA repository for the UserPreferences entity.
 */
@SuppressWarnings("unused")
@Repository
@Primary
public interface CustomUserPreferencesRepository extends JpaRepository<UserPreferences, UserPreferencesTagInfos> {
    List<UserPreferences> findAllByPreferencesIn(Collection<UserPreferencesTagInfos> preferences);
}
