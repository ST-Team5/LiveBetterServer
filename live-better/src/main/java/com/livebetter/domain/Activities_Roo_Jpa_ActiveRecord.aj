// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livebetter.domain;

import com.livebetter.domain.Activities;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Activities_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Activities.entityManager;
    
    public static final List<String> Activities.fieldNames4OrderClauseFilter = java.util.Arrays.asList("");
    
    public static final EntityManager Activities.entityManager() {
        EntityManager em = new Activities().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Activities.countActivitieses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Activities o", Long.class).getSingleResult();
    }
    
    public static List<Activities> Activities.findAllActivitieses() {
        return entityManager().createQuery("SELECT o FROM Activities o", Activities.class).getResultList();
    }
    
    public static List<Activities> Activities.findAllActivitieses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Activities o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Activities.class).getResultList();
    }
    
    public static Activities Activities.findActivities(Long id) {
        if (id == null) return null;
        return entityManager().find(Activities.class, id);
    }
    
    public static List<Activities> Activities.findActivitiesEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Activities o", Activities.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Activities> Activities.findActivitiesEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Activities o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Activities.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Activities.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Activities.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Activities attached = Activities.findActivities(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Activities.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Activities.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Activities Activities.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Activities merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
