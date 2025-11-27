package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentDto;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student createStudent(StudentDto dto) {
        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        Student student = Student.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .branch(dto.getBranch())
                .yop(dto.getYop())
                .active(dto.getActive())
                .build();

        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: " + id));
    }

    public Page<Student> getAllStudents(String branch, Integer yop, Pageable pageable) {
        if (pageable == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pageable must not be null");
        }

        if (branch != null && yop != null) {
            return studentRepository.findByBranchAndYop(branch, yop, pageable);
        } else if (branch != null) {
            return studentRepository.findByBranch(branch, pageable);
        } else if (yop != null) {
            return studentRepository.findByYop(yop, pageable);
        } else {
            return studentRepository.findAll(pageable);
        }
    }

    public Student updateStudent(Long id, StudentDto dto) {
        Student student = getStudentById(id);

        if (!student.getEmail().equals(dto.getEmail()) && studentRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        student.setFullName(dto.getFullName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setBranch(dto.getBranch());
        student.setYop(dto.getYop());
        student.setActive(dto.getActive());

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        student.setActive(false);
        studentRepository.save(student);
    }

    public Pageable createPageable(int page, int size) {
        return PageRequest.of(page, size, Sort.by("id").descending());
    }
}
