package com.livebetter.domain;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(schema = "public",name = "system_users")
@Configurable
public class SystemUser {

	@Id
    @GeneratedValue(generator = "system_users_seq")
	@GenericGenerator(name = "system_users_seq", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "system_users_id_seq"))
    @Column(name = "id")
    private Long id;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	@OneToMany(mappedBy = "userId")
    private Set<UserToPerson> usersToPersonss;

	@ManyToOne
    @JoinColumn(name = "user_role_id", referencedColumnName = "id", nullable = false)
    private SystemUserRole userRoleId;

	@Column(name = "username", length = 45)
    @NotNull
    private String username;

	@Column(name = "password", length = 255)
    @NotNull
    private String password;

	@Column(name = "created_by")
    private Long createdBy;

	@Column(name = "created_datetime")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar createdDatetime;

	@Column(name = "modified_datetime")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar modifiedDatetime;

	public Set<UserToPerson> getUsersToPersonss() {
        return usersToPersonss;
    }

	public void setUsersToPersonss(Set<UserToPerson> usersToPersonss) {
        this.usersToPersonss = usersToPersonss;
    }

	public SystemUserRole getUserRoleId() {
        return userRoleId;
    }

	public void setUserRoleId(SystemUserRole userRoleId) {
        this.userRoleId = userRoleId;
    }

	public String getUsername() {
        return username;
    }

	public void setUsername(String username) {
        this.username = username;
    }

	public String getPassword() {
        return password;
    }

	public void setPassword(String password) {
        this.password = password;
    }

	public Long getCreatedBy() {
        return createdBy;
    }

	public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

	public Calendar getCreatedDatetime() {
        return createdDatetime;
    }

	public void setCreatedDatetime(Calendar createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

	public Calendar getModifiedDatetime() {
        return modifiedDatetime;
    }

	public void setModifiedDatetime(Calendar modifiedDatetime) {
        this.modifiedDatetime = modifiedDatetime;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("usersToPersonss", "userRoleId").toString();
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new SystemUser().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countSystemUserses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SystemUser o", Long.class).getSingleResult();
    }

	public static List<SystemUser> findAllSystemUserses() {
        return entityManager().createQuery("SELECT o FROM SystemUser o", SystemUser.class).getResultList();
    }

	public static List<SystemUser> findAllSystemUserses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM SystemUser o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, SystemUser.class).getResultList();
    }

	public static SystemUser findSystemUsers(Long id) {
        if (id == null) return null;
        return entityManager().find(SystemUser.class, id);
    }

	public static List<SystemUser> findSystemUsersEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SystemUser o", SystemUser.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<SystemUser> findSystemUsersEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM SystemUser o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, SystemUser.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            SystemUser attached = SystemUser.findSystemUsers(this.id);
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
    public SystemUser merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SystemUser merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
