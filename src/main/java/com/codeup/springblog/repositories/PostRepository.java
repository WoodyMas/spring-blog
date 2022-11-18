package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Coffee, Long> {

}