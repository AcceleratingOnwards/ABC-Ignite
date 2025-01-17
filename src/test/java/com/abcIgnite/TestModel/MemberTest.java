package com.abcIgnite.TestModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.abcIgnite.model.Member;

public class MemberTest {

    @Test
    public void testConstructor() {
        Member member = new Member(1L, "John Doe", "john.doe@example.com");

        assertEquals(1L, member.getId());
        assertEquals("John Doe", member.getName());
        assertEquals("john.doe@example.com", member.getEmail());
    }

    @Test
    public void testSettersAndGetters() {
        Member member = new Member();
        member.setId(2L);
        member.setName("Jane Doe");
        member.setEmail("jane.doe@example.com");

        assertEquals(2L, member.getId());
        assertEquals("Jane Doe", member.getName());
        assertEquals("jane.doe@example.com", member.getEmail());
    }

    @Test
    public void testDefaultConstructor() {
        Member member = new Member();

        assertNull(member.getId());
        assertNull(member.getName());
        assertNull(member.getEmail());
    }

    @Test
    public void testNameCannotBeNull() {
        Member member = new Member();
        member.setName(null);
        member.setEmail("john.doe@example.com");

        assertNull(member.getName());  
    }

    @Test
    public void testEmailCannotBeNull() {
        Member member = new Member();
        member.setName("John Doe");
        member.setEmail(null);

        assertNull(member.getEmail());  
    }

    @Test
    public void testEmailUniqueness() {
        Member member1 = new Member(1L, "John Doe", "john.doe@example.com");
        Member member2 = new Member(2L, "Jane Doe", "john.doe@example.com");
        assertEquals(member1.getEmail(), member2.getEmail(), "Emails should be unique");
    }

    @Test
    public void testNameUniqueness() {
        Member member1 = new Member(1L, "John Doe", "john.doe@example.com");
        Member member2 = new Member(2L, "John Doe", "jane.doe@example.com");
        assertEquals(member1.getName(), member2.getName(), "Names should be unique");
    }
}
