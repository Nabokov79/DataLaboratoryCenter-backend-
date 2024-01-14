package ru.nabokovsg.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nabokovsg.data.models.Branch;
import ru.nabokovsg.data.models.Organization;

import java.util.Set;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Set<Branch> findAllByOrganization(Organization organization);

    Branch findByBranch(String branch);
}