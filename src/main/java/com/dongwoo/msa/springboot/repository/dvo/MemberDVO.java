package com.dongwoo.msa.springboot.repository.dvo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class MemberDVO {
    @Id
    private int id;
    private String memberName;
}
