package tn.Sindibad.SVA_Back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.Sindibad.SVA_Back.model.CheckList;

@Repository
public interface CheckListRepository extends JpaRepository<CheckList,Long> {
}
