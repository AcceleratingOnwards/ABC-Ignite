package com.abcIgnite.service;

import com.abcIgnite.model.Member;
import com.abcIgnite.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;


    public Member createMember(Member member) {
        if (member.getName() == null || member.getName().isEmpty()) {
            throw new IllegalArgumentException("Member name cannot be empty");
        }
        if (member.getEmail() == null || member.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Member email cannot be empty");
        }
        return memberRepository.save(member);
    }


    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }


    public Member getMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new RuntimeException("Member not found with ID: " + id);
        }
    }
}
