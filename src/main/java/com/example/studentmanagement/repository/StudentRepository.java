package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

    Page<Student> findByBranch(String branch, Pageable pageable);

    Page<Student> findByYop(Integer yop, Pageable pageable);

    Page<Student> findByBranchAndYop(String branch, Integer yop, Pageable pageable);
}
