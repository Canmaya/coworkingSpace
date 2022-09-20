package ch.zli.coworkingSpace.repository;

import ch.zli.coworkingSpace.model.MemberEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends CrudRepository<MemberEntity, UUID> {
    Optional<MemberEntity> findByFirstName(String findByFirstName);
}