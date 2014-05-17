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
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(schema = "public",name = "person_meals")
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "person_meals", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "mealId", "personId" })
public class PersonMeal {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "person_meals_seq")
	@GenericGenerator(name = "person_meals_seq", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "person_meals_id_seq"))
    @Column(name = "id")
    private Long id;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	@ManyToOne
    @JoinColumn(name = "meal_id", referencedColumnName = "id", nullable = false)
    private Meal mealId;

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

	public Meal getMealId() {
        return mealId;
    }

	public void setMealId(Meal mealId) {
        this.mealId = mealId;
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

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("mealId", "personId").toString();
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("");

	public static final EntityManager entityManager() {
        EntityManager em = new PersonMeal().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countPersonMealses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PersonMeal o", Long.class).getSingleResult();
    }

	public static List<PersonMeal> findAllPersonMealses() {
        return entityManager().createQuery("SELECT o FROM PersonMeal o", PersonMeal.class).getResultList();
    }

	public static List<PersonMeal> findAllPersonMealses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM PersonMeal o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, PersonMeal.class).getResultList();
    }

	public static PersonMeal findPersonMeals(Long id) {
        if (id == null) return null;
        return entityManager().find(PersonMeal.class, id);
    }

	public static List<PersonMeal> findPersonMealsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PersonMeal o", PersonMeal.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<PersonMeal> findPersonMealsEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM PersonMeal o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, PersonMeal.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            PersonMeal attached = PersonMeal.findPersonMeals(this.id);
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
    public PersonMeal merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PersonMeal merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
