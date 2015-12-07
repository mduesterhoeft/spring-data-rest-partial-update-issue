package com.example;

import static javax.persistence.FetchType.EAGER;
import static lombok.AccessLevel.NONE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
public class Person {

    @GeneratedValue
    @Id
    @Setter(NONE)
    private Long id;

    @NonNull
    private String name;

    @ManyToOne(fetch = EAGER, optional = false)
    @JoinColumn(name = "CHILD_ID", nullable = false)
    @NonNull
    private Child child;
}
