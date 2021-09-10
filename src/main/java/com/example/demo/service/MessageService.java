package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository repom;

    public List<Message> findAll()
    {
        return repom.findAll();
    }

    public ResponseEntity<Message> getById(Long id)
    {
        Optional<Message> Om = repom.findById(id);
        if(!Om.isPresent())return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(Om.get());
    }

    public ResponseEntity<Message> update( Message msg)
    {
        Optional<Message> Om= repom.findById(msg.getId());
        if(!Om.isPresent()) return ResponseEntity.notFound().build();
        if(!(Om.get().isMcheck() && msg.isMcheck())){
            Om.get().setMcheck(msg.isMcheck());
        }
        return ResponseEntity.ok(repom.save(Om.get()));
    }

    public ResponseEntity<Message> save(Message msg)
    {
        Date d = new Date();
        msg.setTime(new java.sql.Date(d.getYear(),d.getMonth(),d.getDate()));
        msg.setMcheck(false);
        return ResponseEntity.ok(repom.save(msg));
    }

    public List<Message> getAllBySortID(boolean i) {
        Direction d = Direction.ASC,d1=Direction.DESC;
        if(!i)return repom.findAll(Sort.by(d, "id"));
        return repom.findAll(Sort.by(d1, "id"));
    }

    public List<Message> getAllBySortName(boolean i) {
        Direction d = Direction.ASC,d1=Direction.DESC;
        if(!i)return repom.findAll(Sort.by(d, "time"));
        return repom.findAll(Sort.by(d1, "time"));
    }

    public ResponseEntity<Message> deleteById(Long id) {
        Optional<Message> Om = repom.findById(id);
        if(!Om.isPresent()) return ResponseEntity.notFound().build();
        if(!Om.get().isMcheck()) return ResponseEntity.notFound().build();
        repom.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public List<Message> getAllBykeyword(String i) {
        return repom.findAllByKeyWord(i);
    }

    public List<Message> getAllCheck(boolean check) {
        return repom.findAllByStatus(check);
    }
    
}
