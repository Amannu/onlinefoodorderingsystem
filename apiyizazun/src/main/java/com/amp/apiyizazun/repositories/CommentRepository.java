package com.amp.apiyizazun.repositories;

import org.springframework.data.repository.CrudRepository;

import com.amp.apiyizazun.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
