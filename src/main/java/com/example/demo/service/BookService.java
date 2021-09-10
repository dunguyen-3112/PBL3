package com.example.demo.service;

import com.example.demo.model.Registered;
import com.example.demo.model.UserS;
import com.example.demo.model.Book;
import com.example.demo.repository.RegisteredRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserSRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.GenreRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@AllArgsConstructor
public class BookService {

    private static String path = "D:\\images\\s\\";

    // private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final BookRepository repob;

    private final RegisteredRepository repor;

    private final UserSRepository repou;

    private final GenreRepository repogenre;

    static Logger log = LoggerFactory.getLogger(BookService.class.getName());

    public List<Book> getAll() {
        List<Book> books = repob.findAllByTop();
        for (Book i : books) {
            Optional<Registered> rn = repor.findByIdStatusTrue(i.getId(), true);
            if (rn.isPresent()) {
                Registered r = rn.get();
                Date d = r.getRegistrationDate();
                int th = r.getTime() + d.getMonth();
                if (th % 12 == 0)
                    d.setYear(d.getYear() + 1);
                else {
                    d.setMonth(th - d.getMonth());
                }
                if (d.after(new Date())) {
                    i.setStatus(true);
                    r.setStatus(false);
                }
                repob.save(i);
                repor.save(r);
            }
        }
        return repob.findAllByTop();
    }

    public Book save(Book book) {
        Object O = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserS user1 = repou.findByEmailNumber(((UserDetails) O).getUsername());
        book.setIdUser(user1.getId());
        return repob.save(book);
    }

    public ResponseEntity<Book> delete(Long id) {
        Optional<Book> Obook = repob.findById(id);
        if (!Obook.isPresent())
            return ResponseEntity.notFound().build();
        if(!repogenre.findById(Obook.get().getIdgenre()).isPresent())return ResponseEntity.notFound().build();
        Optional<UserS> Ou = repou.findById(Obook.get().getIdUser());
        if (!Ou.isPresent())
            return ResponseEntity.notFound().build();
        if (Ou.get().getIdRole() != 1) {
            if (!Objects.equals(Ou.get().getId(), Obook.get().getIdUser()))
                return ResponseEntity.notFound().build();
        }
        File file = new File(path + id + ".jpg");
        file.delete();
        List<Registered> dks = repor.findAll();
        dks.forEach(e -> {
            repor.deleteById(e.getId());
        });
        repob.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Book> getById(Long id) {
        Optional<Book> book = repob.findById(id);
        if (!book.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(book.get());
    }

    public List<Book> getByIdgenre(Long id) {
        return repob.findAllByIdgenre(id);
    }

    public ResponseEntity<Book> update(Book book, MultipartFile file) {
        Optional<Book> Obook = repob.findById(book.getId());
        if (!Obook.isPresent())
            return ResponseEntity.notFound().build();
        Optional<UserS> Ou = repou.findById(Obook.get().getIdUser());
        if (!Ou.isPresent())
            return ResponseEntity.notFound().build();
        if (Ou.get().getIdRole() != 1) {
            if (!Objects.equals(Ou.get().getId(), Obook.get().getIdUser()))
                return ResponseEntity.notFound().build();
        }
        if (!Objects.equals(Obook.get().getName(), book.getName()))
            Obook.get().setName(book.getName());
        if (!Objects.equals(Obook.get().getSale(), book.getSale()))
            Obook.get().setSale(book.getSale());
        if (!Objects.equals(Obook.get().getPublisher(), book.getPublisher()))
            Obook.get().setPublisher(book.getPublisher());
        if (!Objects.equals(Obook.get().getIdgenre(), book.getIdgenre()))
            Obook.get().setIdgenre(book.getIdgenre());
        if (!Objects.equals(Obook.get().getReleaDate(), book.getReleaDate()))
            Obook.get().setReleaDate(book.getReleaDate());
        if (!Objects.equals(Obook.get().getPages(), book.getPages()))
            Obook.get().setPages(book.getPages());
        if (!Objects.equals(Obook.get().getAuthor(), book.getAuthor()))
            Obook.get().setAuthor(book.getAuthor());

        if (!Objects.equals(Obook.get().getDescription(), book.getDescription()))
            Obook.get().setDescription(book.getDescription());
        return ResponseEntity.ok(repob.save(Obook.get()));
    }

    public ResponseEntity<Book> update(MultipartFile uploadfile, Long id) {
        if ((uploadfile != null) && (!uploadfile.isEmpty())) {
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(path + id + ".jpg");
                fos.write(uploadfile.getBytes());
                fos.close();
            } catch (IOException e) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok().build();
    }

    public List<Book> getAllBySortID(boolean i) {
        Direction d = Direction.ASC, d1 = Direction.DESC;
        if (!i)
            return repob.findAll(Sort.by(d, "id"));
        return repob.findAll(Sort.by(d1, "id"));
    }

    public List<Book> getAllBySortName(boolean i) {
        Direction d = Direction.ASC, d1 = Direction.DESC;
        if (!i)
            return repob.findAll(Sort.by(d, "name"));
        return repob.findAll(Sort.by(d1, "name"));
    }

    public List<Book> findAllBookByKeyWord(String keyword) {
        return repob.findAllByKeyWord(keyword);
    }

    public List<Book> getAllByCheck(boolean i) {
        return repob.findAllByCheck(i);
    }

    public boolean check(Long idB, Long idU) {
        Object O = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserS user1 = repou.findByEmailNumber(((UserDetails) O).getUsername());
        Optional<Book> Ob = repob.findById(idB);
        Optional<UserS> Ou = repou.findById(idU);
        if (!Ob.isPresent())
            return false;
        if (!Ou.isPresent())
            return false;
        if (user1.getIdRole() == 1)
            return true;
        if (!Objects.equals(Ob.get().getIdUser(), user1.getId()))
            return false;
        else
            return true;
    }

}
