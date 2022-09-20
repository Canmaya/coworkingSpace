package ch.zli.coworkingSpace.repository;

import ch.zli.coworkingSpace.model.MemberEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByEmail(String findByEmail);

}