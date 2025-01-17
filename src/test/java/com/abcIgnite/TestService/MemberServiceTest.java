package com.abcIgnite.TestService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.abcIgnite.model.Member;
import com.abcIgnite.repository.MemberRepository;
import com.abcIgnite.service.MemberService;

public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private Member member;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        member = new Member();
        member.setId(1L);
        member.setName("John Doe");
        member.setEmail("john.doe@example.com");
    }

    @Test
    public void testCreateMember_Success() {
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        Member result = memberService.createMember(member);

        verify(memberRepository, times(1)).save(any(Member.class));
        assert(result.getName().equals("John Doe"));
        assert(result.getEmail().equals("john.doe@example.com"));
    }

    @Test
    public void testCreateMember_EmptyName() {
        member.setName("");

        try {
            memberService.createMember(member);
        } catch (IllegalArgumentException e) {
            assert(e.getMessage().equals("Member name cannot be empty"));
        }
    }

    @Test
    public void testCreateMember_EmptyEmail() {
        member.setEmail("");

        try {
            memberService.createMember(member);
        } catch (IllegalArgumentException e) {
            assert(e.getMessage().equals("Member email cannot be empty"));
        }
    }

    @Test
    public void testGetAllMembers() {
        Member anotherMember = new Member();
        anotherMember.setId(2L);
        anotherMember.setName("Jane Doe");
        anotherMember.setEmail("jane.doe@example.com");

        when(memberRepository.findAll()).thenReturn(Arrays.asList(member, anotherMember));

        List<Member> members = memberService.getAllMembers();

        assert(members.size() == 2);
        assert(members.get(0).getName().equals("John Doe"));
        assert(members.get(1).getName().equals("Jane Doe"));
    }

    @Test
    public void testGetMemberById_Success() {
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        Member result = memberService.getMemberById(1L);

        assert(result.getName().equals("John Doe"));
        assert(result.getEmail().equals("john.doe@example.com"));
    }

    @Test
    public void testGetMemberById_NotFound() {
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            memberService.getMemberById(1L);
        } catch (RuntimeException e) {
            assert(e.getMessage().equals("Member not found with ID: 1"));
        }
    }
}
