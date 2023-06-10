package com.docmanager.docmanagerbackend.employee;

import com.docmanager.docmanagerbackend.department.Department;
import com.docmanager.docmanagerbackend.document.Document;
import com.docmanager.docmanagerbackend.task.Task;
import com.docmanager.docmanagerbackend.taskupdate.TaskUpdate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany(mappedBy = "author")
    private List<Document> documents;

    @OneToMany(mappedBy = "author")
    private List<Task> tasksAssignedToOthers;

    @OneToMany(mappedBy = "employeeAssigned")
    private List<Task> tasksAssignedToThis;

//    @OneToMany(mappedBy = "author")
//    private List<TaskUpdate> tasksUpdates;

    @ManyToOne
    @JoinColumn(name = "dep_id") // add_nullable = false
    private Department department;


    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
