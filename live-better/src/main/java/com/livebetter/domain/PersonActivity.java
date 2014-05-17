package com.livebetter.domain;
import java.math.BigDecimal;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(schema = "public",name = "person_activities")
@Configurable
public class PersonActivity {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("activityId", "personId").toString();
    }

	@Id
    @GeneratedValue(generator = "person_activities_seq")
	@GenericGenerator(name = "person_activities_seq", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "person_activities_id_seq"))
    @Column(name = "id")
    private Long id;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new PersonActivity().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countPersonActivitieses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PersonActivity o", Long.class).getSingleResult();
    }

	public static List<PersonActivity> findAllPersonActivitieses() {
        return entityManager().createQuery("SELECT o FROM PersonActivity o", PersonActivity.class).getResultList();
    }

	public static List<PersonActivity> findAllPersonActivitieses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM PersonActivity o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, PersonActivity.class).getResultList();
    }

	public static PersonActivity findPersonActivities(Long id) {
        if (id == null) return null;
        return entityManager().find(PersonActivity.class, id);
    }

	public static List<PersonActivity> findPersonActivitiesEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PersonActivity o", PersonActivity.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<PersonActivity> findPersonActivitiesEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM PersonActivity o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, PersonActivity.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            PersonActivity attached = PersonActivity.findPersonActivities(this.id);
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
    public PersonActivity merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PersonActivity merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = false)
    private Activity activityId;

	@ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private Person personId;

	@Column(name = "is_consumed")
    private Boolean isConsumed;

	@Column(name = "datetime_of_consumtion")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar datetimeOfConsumtion;

	@Column(name = "quantity", precision = 18, scale = 4)
    private BigDecimal quantity;

	@Column(name = "created_by")
    private Long createdBy;

	@Column(name = "created_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar createdDatetime;

	public Activity getActivityId() {
        return activityId;
    }

	public void setActivityId(Activity activityId) {
        this.activityId = activityId;
    }

	public Person getPersonId() {
        return personId;
    }

	public void setPersonId(Person personId) {
        this.personId = personId;
    }

	public Boolean getIsConsumed() {
        return isConsumed;
    }

	public void setIsConsumed(Boolean isConsumed) {
        this.isConsumed = isConsumed;
    }

	public Calendar getDatetimeOfConsumtion() {
        return datetimeOfConsumtion;
    }

	public void setDatetimeOfConsumtion(Calendar datetimeOfConsumtion) {
        this.datetimeOfConsumtion = datetimeOfConsumtion;
    }

	public BigDecimal getQuantity() {
        return quantity;
    }

	public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
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
}
