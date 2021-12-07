package com.dongwoo.msa.springboot.rest.rvo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class MemberRVO {
    @Id
    private int id;
    private String memberName;
}
