package com.livebetter.domain;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(schema = "public",name = "users_to_persons")
public class UserToPerson {

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new UserToPerson().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countUsersToPersonses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UserToPerson o", Long.class).getSingleResult();
    }

	public static List<UserToPerson> findAllUsersToPersonses() {
        return entityManager().createQuery("SELECT o FROM UserToPerson o", UserToPerson.class).getResultList();
    }

	public static List<UserToPerson> findAllUsersToPersonses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM UserToPerson o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, UserToPerson.class).getResultList();
    }

	public static UserToPerson findUsersToPersons(Long id) {
        if (id == null) return null;
        return entityManager().find(UserToPerson.class, id);
    }

	public static List<UserToPerson> findUsersToPersonsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UserToPerson o", UserToPerson.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<UserToPerson> findUsersToPersonsEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM UserToPerson o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, UserToPerson.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UserToPerson attached = UserToPerson.findUsersToPersons(this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public UserToPerson merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UserToPerson merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }


	@Id
    @GeneratedValue(generator = "users_to_persons_seq")
	@GenericGenerator(name = "users_to_persons_seq", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "users_to_persons_id_seq"))
    @Column(name = "id")
    private Long id;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("personId", "userId").toString();
    }

	@ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private Person personId;

	@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private SystemUser userId;

	public Person getPersonId() {
        return personId;
    }

	public void setPersonId(Person personId) {
        this.personId = personId;
    }

	public SystemUser getUserId() {
        return userId;
    }

	public void setUserId(SystemUser userId) {
        this.userId = userId;
    }
}
