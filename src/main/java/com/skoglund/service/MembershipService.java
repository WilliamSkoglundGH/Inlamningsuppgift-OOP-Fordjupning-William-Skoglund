package com.skoglund.service;

import com.skoglund.entity.Member;
import com.skoglund.repository.MemberRegistry;
import javafx.scene.control.Alert;

import java.io.IOException;

public class MembershipService {
    private MemberRegistry memberRegistry;

    public MembershipService(){

    }

    public MembershipService(MemberRegistry memberRegistry){
       this.memberRegistry = memberRegistry;
    }

    public void addNewMember(Member member) {
        memberRegistry.addMember(member);
    }

    public void saveMemberListToFile() throws IOException {
            memberRegistry.saveMemberListToFile();
    }

    public Member searchAndGetMember(String id){
        return memberRegistry.searchAndReturnMember(id);
    }

    public void changeMemberInfo(Member member, String newName, String newAgeGroup){
        member.setName(newName);
        member.setAgeGroup(newAgeGroup);

    }
}
