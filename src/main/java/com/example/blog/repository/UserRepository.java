package com.example.blog.repository;



import com.example.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

//find user by username containing
    Iterable<User> findAllByUsernameContaining(String username);



}
