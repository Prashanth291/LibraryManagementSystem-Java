package com.library.LibrarySystem.services;

import com.library.LibrarySystem.models.Member;
import com.library.LibrarySystem.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean addMember(String memberId, String name, String email, String phone) {
        if (memberRepository.existsById(memberId)) {
            return false;
        }
        if (memberRepository.findByEmail(email).isPresent()) {
            return false;
        }

        Member member = new Member(memberId, name, email, phone);
        memberRepository.save(member);
        return true;
    }

    public boolean updateMember(String memberId, String name, String email, String phone) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isEmpty()) {
            return false;
        }

        Member member = optionalMember.get();
        member.setName(name);
        member.setEmail(email);
        member.setPhone(phone);
        
        memberRepository.save(member); 
        return true;
    }

    public boolean deleteMember(String memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isEmpty()) {
            return false;
        }
        
        Member member = optionalMember.get();
        if (!member.getBorrowedBookIds().isEmpty()) {
            return false;
        }

        memberRepository.deleteById(memberId);
        return true;
    }

    public Member findMemberById(String memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }
    
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
    public String generateMemberId() {
        long count = memberRepository.count();
        return String.format("M%03d", count + 1);
    }
    
}