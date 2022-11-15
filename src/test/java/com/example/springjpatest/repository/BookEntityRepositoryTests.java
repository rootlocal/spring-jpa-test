package com.example.springjpatest.repository;

import com.example.springjpatest.jpa.entity.BookEntity;
import com.example.springjpatest.jpa.entity.BookTypeEnum;
import com.example.springjpatest.jpa.repository.BookEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles(value = {"production", "test"})
public class BookEntityRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BookEntityRepository repository;

    @Test
    void testCreate() throws Exception {
        entityManager.persist(new BookEntity("Ahgu3She", BookTypeEnum.BOOK_TYPE_CLASSICS));
        Optional<BookEntity> optional = repository.findByName("Ahgu3She");
        assertThat(optional.get().getName()).isEqualTo("Ahgu3She");
        assertThat(optional.get().getType()).isEqualTo(BookTypeEnum.BOOK_TYPE_CLASSICS);
    }

    @Test
    void testNotFound() throws Exception {
        Optional<BookEntity> notFound = repository.findByName("ewrrtes");
        assertThat(notFound).isEmpty();
    }

    @Test
    void testUpdate() throws Exception {
        entityManager.persist(new BookEntity("hjhkjsahk", BookTypeEnum.BOOK_TYPE_CLASSICS));
        Optional<BookEntity> optional = repository.findByName("hjhkjsahk");
        optional.ifPresent(entity -> {
            entity.setName("asdfghjkl");
            repository.save(entity);
        });

        Optional<BookEntity> updated = repository.findByName("asdfghjkl");
        assertThat(updated.get().getName()).isEqualTo("asdfghjkl");

        Optional<BookEntity> notFound = repository.findByName("hjhkjsahk");
        assertThat(notFound).isEmpty();
    }

}
