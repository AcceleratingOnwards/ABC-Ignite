package com.abcIgnite.TestController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.abcIgnite.controller.MemberController;
import com.abcIgnite.model.Member;
import com.abcIgnite.service.MemberService;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    @Test
    void testCreateMember_Success() {
        Member member = new Member();
        member.setId(1L);
        member.setName("John Doe");
        member.setEmail("johndoe@example.com");

        when(memberService.createMember(member)).thenReturn(member);

        ResponseEntity<Member> response = memberController.createMember(member);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(member, response.getBody());

        verify(memberService, times(1)).createMember(member);
    }

    @Test
    void testCreateMember_BadRequest() {
        Member member = new Member();
        member.setId(1L);
        member.setName("John Doe");
        member.setEmail("johndoe@example.com");

        when(memberService.createMember(member)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<Member> response = memberController.createMember(member);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

        verify(memberService, times(1)).createMember(member);
    }

    @Test
    void testGetAllMembers_Success() {
        Member member1 = new Member(1L, "John Doe", "johndoe@example.com");
        Member member2 = new Member(2L, "Jane Smith", "janesmith@example.com");
        List<Member> members = Arrays.asList(member1, member2);

        when(memberService.getAllMembers()).thenReturn(members);

        ResponseEntity<List<Member>> response = memberController.getAllMembers();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("John Doe", response.getBody().get(0).getName());

        verify(memberService, times(1)).getAllMembers();
    }

    @Test
    void testGetAllMembers_EmptyList() {
        when(memberService.getAllMembers()).thenReturn(Arrays.asList());

        ResponseEntity<List<Member>> response = memberController.getAllMembers();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());

        verify(memberService, times(1)).getAllMembers();
    }

    @Test
    void testGetMemberById_Success() {
        Long memberId = 1L;
        Member member = new Member(memberId, "John Doe", "johndoe@example.com");

        when(memberService.getMemberById(memberId)).thenReturn(member);

        ResponseEntity<Member> response = memberController.getMemberById(memberId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(member, response.getBody());

        verify(memberService, times(1)).getMemberById(memberId);
    }

    @Test
    void testGetMemberById_NotFound() {
        Long memberId = 1L;

        when(memberService.getMemberById(memberId)).thenThrow(RuntimeException.class);

        ResponseEntity<Member> response = memberController.getMemberById(memberId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(memberService, times(1)).getMemberById(memberId);
    }
}
