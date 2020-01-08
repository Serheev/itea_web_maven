package com.serheev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.log4j.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "skill")
@NamedQuery(name = "SkillEntity.getAll", query = "SELECT s FROM SkillEntity s")
public class SkillEntity extends BaseEntity {
    private static Logger log = Logger.getLogger(SkillEntity.class);

    @Column(name = "industry", length = 128)
    private String industry;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "skills")
    private Set<DeveloperEntity> developers;

    @PrePersist
    public void callbackPrePersist() {
        log.info("+++ Executed a PrePersist callback method!");
    }

    @PostPersist
    public void callbackPostPersist() {
        log.info("+++ Executed a PostPersist callback method!");
    }

    @PreUpdate
    public void callbackPreUpdate() {
        log.info("+++ Executed a PreUpdate callback method!");
    }

    @PostUpdate
    public void callbackPostUpdate() {
        log.info("+++ Executed a PostUpdate callback method!");
    }

    @PostLoad
    public void callbackPostLoad() {
        log.info("+++ Executed a PostLoad callback method!");
    }

    @PreRemove
    public void callbackPreRemove() {
        log.info("+++ Executed a PreRemove callback method!");
    }
}
