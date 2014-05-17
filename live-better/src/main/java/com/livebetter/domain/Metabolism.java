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

@Configurable
@Entity
@Table(schema = "public",name = "metabolisms")
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "metabolisms", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "personss" })
public class Metabolism {

	@OneToMany(mappedBy = "metabolismId")
    private Set<Person> personss;

	@Column(name = "name", length = 45)
    @NotNull
    private String name;

	@Column(name = "created_by")
    private Long createdBy;

	@Column(name = "created_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar createdDatetime;

	@Column(name = "modified_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar modifiedDatetime;

	public Set<Person> getPersonss() {
        return personss;
    }

	public void setPersonss(Set<Person> personss) {
        this.personss = personss;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
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
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("personss").toString();
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new Metabolism().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countMetabolismses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Metabolism o", Long.class).getSingleResult();
    }

	public static List<Metabolism> findAllMetabolismses() {
        return entityManager().createQuery("SELECT o FROM Metabolism o", Metabolism.class).getResultList();
    }

	public static List<Metabolism> findAllMetabolismses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Metabolism o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Metabolism.class).getResultList();
    }

	public static Metabolism findMetabolisms(Long id) {
        if (id == null) return null;
        return entityManager().find(Metabolism.class, id);
    }

	public static List<Metabolism> findMetabolismsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Metabolism o", Metabolism.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Metabolism> findMetabolismsEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Metabolism o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Metabolism.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Metabolism attached = Metabolism.findMetabolisms(this.id);
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
    public Metabolism merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Metabolism merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "metabolisms_seq")
	@GenericGenerator(name = "metabolisms_seq", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "metabolisms_id_seq"))
    @Column(name = "id")
    private Long id;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }
}
