package com.library.LibrarySystem.controllers;

import com.library.LibrarySystem.models.Member;
import com.library.LibrarySystem.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public String listAllMembers(Model model) {
        List<Member> memberList = memberService.getAllMembers();
        model.addAttribute("members", memberList);
        return "members-list"; 
    }
    @GetMapping("/members/add")
    public String showAddMemberForm(Model model) {
        model.addAttribute("member", new Member("", "", "", ""));
        return "add-member"; 
    }

    @PostMapping("/members/save")
    public String saveNewMember(@ModelAttribute("member") Member member, RedirectAttributes redirectAttributes) {
        String newMemberId = memberService.generateMemberId();

        memberService.addMember(
            newMemberId,
            member.getName(),
            member.getEmail(),
            member.getPhone()
        );

        redirectAttributes.addFlashAttribute("successMessage", "Member added successfully!");

        return "redirect:/members";
    }
    @GetMapping("/members/edit/{id}")
    public String showEditMemberForm(@PathVariable("id") String memberId, Model model) {
        Member member = memberService.findMemberById(memberId);
        model.addAttribute("member", member);
        return "edit-member"; 
    }

    @PostMapping("/members/update")
    public String updateMember(@ModelAttribute("member") Member member, RedirectAttributes redirectAttributes) {
        memberService.updateMember(
            member.getMemberId(),
            member.getName(),
            member.getEmail(),
            member.getPhone()
        );
        redirectAttributes.addFlashAttribute("successMessage", "Member updated successfully!");
        return "redirect:/members";
    }
    @GetMapping("/members/delete/{id}")
    public String deleteMember(@PathVariable("id") String memberId, RedirectAttributes redirectAttributes) {
        memberService.deleteMember(memberId);
        redirectAttributes.addFlashAttribute("successMessage", "Member deleted successfully!");
        return "redirect:/members";
    }
}