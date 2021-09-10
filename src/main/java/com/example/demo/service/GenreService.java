package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Genre;
import com.example.demo.model.Registered;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.RegisteredRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GenreService {

    private final GenreRepository repoGenre;

    private final BookRepository repoBook;

    private final RegisteredRepository repore;

    public List<Genre> getAll(){
        return repoGenre.findAll();
    }

    public Genre save(Genre theLoai) {
        return repoGenre.save(theLoai);
    }

    public ResponseEntity<Genre> delete(Long id) {
        Optional<Genre> Ogenre = repoGenre.findById(id);
        if (!Ogenre.isPresent())
            return ResponseEntity.notFound().build();
        List<Book> sachs = repoBook.findAllByIdgenre(Ogenre.get().getId());
        sachs.forEach(bi->{
            List<Registered> res = repore.findAllByIdBook(bi.getId());
            repore.deleteAll(res);
        });
        repoBook.deleteAll(sachs);
        repoGenre.delete(Ogenre.get());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Genre> getById( Long id) {
        Optional<Genre> otheLoai = repoGenre.findById(id);
        if (!otheLoai.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(otheLoai.get());
    }

    public  ResponseEntity<Genre> Update(Genre genre)
    {
        Optional<Genre> otheLoai = repoGenre.findById(genre.getId());
        if (!otheLoai.isPresent())
            return ResponseEntity.notFound().build();
        if (!Objects.equals(otheLoai.get().getName(), genre.getName())) {
            otheLoai.get().setName(genre.getName());
        }
        return ResponseEntity.ok(repoGenre.save(otheLoai.get()));
    }

    public List<Genre> getAllBySortID(boolean i) {
        Direction d = Direction.ASC,d1 = Direction.DESC;
        if(!i)return repoGenre.findAll(Sort.by(d, "id"));
        return repoGenre.findAll(Sort.by(d1, "id"));
    }

    public List<Genre> getAllBySortName(boolean i) {
        Direction d = Direction.ASC,d1 = Direction.DESC;
        if(!i)return repoGenre.findAll(Sort.by(d, "name"));
        return repoGenre.findAll(Sort.by(d1, "name"));
    }

    public List<Genre> getAllByKeyword(String i) {
        return repoGenre.findAllByKeyword(i);
    }
}