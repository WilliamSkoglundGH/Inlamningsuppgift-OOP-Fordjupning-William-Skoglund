package com.skoglund.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.skoglund.entity.Member;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MemberRegistry {
    private final ObservableList<Member> memberList = FXCollections.observableArrayList();

    public MemberRegistry() throws IOException {
        loadMemberListFromFile();
    }

    public void addMember(Member member){
        memberList.add(member);
    }

    public ObservableList<Member> getMemberList(){
        return memberList;
    }

    public void saveMemberListToFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

            mapper.writeValue(new File("members.json"), memberList);
    }

    private List<Member> getMembersFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
            File memberJsonFile = new File("members.json");
            if(!memberJsonFile.exists() || memberJsonFile.length() == 0){
                mapper.writeValue(memberJsonFile, new ArrayList<Member>());
            }
            List<Member> fromFile = Arrays.asList(mapper.readValue(new File("members.json"),
                    Member[].class));
            return fromFile;
    }

    public void loadMemberListFromFile() throws IOException {
        memberList.clear();
        memberList.setAll(getMembersFromFile());
    }

    public Member searchAndReturnMember(String id){
            return memberList.stream().filter(member-> member.getId().equals(id))
                    .findAny().orElse(null);
    }
}
