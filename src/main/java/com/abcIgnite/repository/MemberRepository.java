package com.abcIgnite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcIgnite.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
