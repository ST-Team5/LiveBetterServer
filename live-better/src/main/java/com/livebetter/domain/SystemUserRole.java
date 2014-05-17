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
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(schema = "public",name = "system_user_roles")
@Configurable
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "system_user_roles", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "systemUserss" })
public class SystemUserRole {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("systemUserss").toString();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system_user_roles_seq")
	@GenericGenerator(name = "system_user_roles_seq", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "system_user_roles_id_seq"))
    @Column(name = "id")
    private Long id;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	@OneToMany(mappedBy = "userRoleId")
    private Set<SystemUser> systemUserss;

	@Column(name = "name", length = 45)
    @NotNull
    private String name;

	@Column(name = "description", length = 255)
    @NotNull
    private String description;

	@Column(name = "created_by")
    private Long createdBy;

	@Column(name = "level")
    @NotNull
    private Long level;

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

	public Set<SystemUser> getSystemUserss() {
        return systemUserss;
    }

	public void setSystemUserss(Set<SystemUser> systemUserss) {
        this.systemUserss = systemUserss;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String getDescription() {
        return description;
    }

	public void setDescription(String description) {
        this.description = description;
    }

	public Long getCreatedBy() {
        return createdBy;
    }

	public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

	public Long getLevel() {
        return level;
    }

	public void setLevel(Long level) {
        this.level = level;
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

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new SystemUserRole().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countSystemUserRoleses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SystemUserRole o", Long.class).getSingleResult();
    }

	public static List<SystemUserRole> findAllSystemUserRoleses() {
        return entityManager().createQuery("SELECT o FROM SystemUserRole o", SystemUserRole.class).getResultList();
    }

	public static List<SystemUserRole> findAllSystemUserRoleses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM SystemUserRole o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, SystemUserRole.class).getResultList();
    }

	public static SystemUserRole findSystemUserRoles(Long id) {
        if (id == null) return null;
        return entityManager().find(SystemUserRole.class, id);
    }

	public static List<SystemUserRole> findSystemUserRolesEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SystemUserRole o", SystemUserRole.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<SystemUserRole> findSystemUserRolesEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM SystemUserRole o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, SystemUserRole.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            SystemUserRole attached = SystemUserRole.findSystemUserRoles(this.id);
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
    public SystemUserRole merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SystemUserRole merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
