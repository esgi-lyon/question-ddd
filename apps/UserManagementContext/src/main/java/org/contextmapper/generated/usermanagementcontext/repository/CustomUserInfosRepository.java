package org.contextmapper.generated.usermanagementcontext.repository;

import org.contextmapper.generated.usermanagementcontext.domain.UserInfos;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary
@Repository
public interface CustomUserInfosRepository extends UserInfosRepository {
    Optional<UserInfos> findByMail(String email);
}
