package ch.zli.coworkingSpace.service;

import ch.zli.coworkingSpace.model.MemberEntity;
import ch.zli.coworkingSpace.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService {
    private final MemberRepository repository;

    @Autowired
    public MemberService(MemberRepository MemberRepository) {
        this.repository = MemberRepository;
    }

    public List<MemberEntity> loadAll() {
        log.info("Executing find all Members ...");
        return (List<MemberEntity>) repository.findAll();
    }

    public Optional<MemberEntity> loadOne(Long MemberId) {
        log.info("Executing find Member with id " + MemberId + " ...");
        return repository.findById(MemberId);
    }

    public MemberEntity create(MemberEntity member) {
        log.info("Executing create Member with id " + member.getId() + " ...");
        member.setPassword(BCrypt.hashpw(member.getPassword(), BCrypt.gensalt()));
        return repository.save(member);
    }

    public MemberEntity update(MemberEntity updateMember) {
        log.info("Executing update Member with id " + updateMember.getId() + " ...");
        updateMember.setPassword(BCrypt.hashpw(updateMember.getPassword(), BCrypt.gensalt()));
        return repository.save(updateMember);
    }

    public void delete(Long MemberId) {
        log.info("Executing delete Member with id " + MemberId + " ...");
        repository.deleteById(MemberId);
    }

}
