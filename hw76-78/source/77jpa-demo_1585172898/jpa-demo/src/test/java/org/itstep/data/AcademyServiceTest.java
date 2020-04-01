package org.itstep.data;

import org.itstep.dto.StudentDto;
import org.itstep.model.Group;
import org.itstep.model.Student;
import org.itstep.repository.StudentRepository;
import org.itstep.service.AcademyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

// Подключаем mockito
@ExtendWith(MockitoExtension.class)
public class AcademyServiceTest {

    // Мокаем репозиторий
    @Mock
    StudentRepository studentRepository;

    // Внедряем репозиторий в сервис
    @InjectMocks
    AcademyService academyService;

//    static class FakeStudentRepository implements StudentRepository {
//
//        @Override
//        public List<Student> findAll() {
//            return null;
//        }
//
//        @Override
//        public List<Student> findAll(Sort sort) {
//            return null;
//        }
//
//        @Override
//        public Page<Student> findAll(Pageable pageable) {
//            return null;
//        }
//
//        @Override
//        public List<Student> findAllById(Iterable<Integer> integers) {
//            return null;
//        }
//
//        @Override
//        public long count() {
//            return 0;
//        }
//
//        @Override
//        public void deleteById(Integer integer) {
//
//        }
//
//        @Override
//        public void delete(Student student) {
//
//        }
//
//        @Override
//        public void deleteAll(Iterable<? extends Student> iterable) {
//
//        }
//
//        @Override
//        public void deleteAll() {
//
//        }
//
//        @Override
//        public <S extends Student> S save(S s) {
//            return null;
//        }
//
//        @Override
//        public <S extends Student> List<S> saveAll(Iterable<S> entities) {
//            return null;
//        }
//
//        @Override
//        public Optional<Student> findById(Integer integer) {
//            return Optional.of(new Student(1, "Вася", "Пупкин",
//                    LocalDate.of(2001, 1, 1),
//                    new Group(1, "Java summer 2019", null, null)));
//        }
//
//        @Override
//        public boolean existsById(Integer integer) {
//            return false;
//        }
//
//        @Override
//        public void flush() {
//
//        }
//
//        @Override
//        public <S extends Student> S saveAndFlush(S entity) {
//            return null;
//        }
//
//        @Override
//        public void deleteInBatch(Iterable<Student> entities) {
//
//        }
//
//        @Override
//        public void deleteAllInBatch() {
//
//        }
//
//        @Override
//        public Student getOne(Integer integer) {
//            return null;
//        }
//
//        @Override
//        public <S extends Student> Optional<S> findOne(Example<S> example) {
//            return Optional.empty();
//        }
//
//        @Override
//        public <S extends Student> List<S> findAll(Example<S> example) {
//            return null;
//        }
//
//        @Override
//        public <S extends Student> List<S> findAll(Example<S> example, Sort sort) {
//            return null;
//        }
//
//        @Override
//        public <S extends Student> Page<S> findAll(Example<S> example, Pageable pageable) {
//            return null;
//        }
//
//        @Override
//        public <S extends Student> long count(Example<S> example) {
//            return 0;
//        }
//
//        @Override
//        public <S extends Student> boolean exists(Example<S> example) {
//            return false;
//        }
//    }
//
//    @BeforeEach
//    void init() {
//        // Dummy - null аргумент groupRepository
//        // Fake/Stub - new FakeStudentRepository()
////        StudentRepository studentRepository = mock(StudentRepository.class);
////        MockitoAnnotations.initMocks(this);
//
//
//        //academyService = new AcademyService(studentRepository, null);
//    }

    @Test
    void getStudentDto() {
        when(studentRepository.findById(1))
                .thenReturn(Optional.of(new Student(1, "Вася", "Пупкин",
                        LocalDate.of(2001, 1, 1),
                        new Group(1, "Java summer 2019", null, null))));

        StudentDto studentDto = academyService.getStudentDto(1);

        // проверяем, что метод findById репозитория был вызван ровно 1 раз
        verify(studentRepository, times(1)).findById(1);

        // Проверяем DTO
        assertNotNull(studentDto);
        assertEquals(1, studentDto.getId());
        assertEquals("Вася", studentDto.getFirstName());
        assertEquals("Пупкин", studentDto.getLastName());
        assertEquals("Java summer 2019", studentDto.getGroupName());
        assertEquals(1, studentDto.getGroupId());
    }

}
