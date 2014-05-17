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

@Entity
@Table(schema = "public",name = "person_drinks")
@Configurable
@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "person_drinks", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "drinkId", "personId" })
public class PersonDrink {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("drinkId", "personId").toString();
    }

	@ManyToOne
    @JoinColumn(name = "drink_id", referencedColumnName = "id", nullable = false)
    private Drink drinkId;

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

	public Drink getDrinkId() {
        return drinkId;
    }

	public void setDrinkId(Drink drinkId) {
        this.drinkId = drinkId;
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

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "person_drinks_seq")
	@GenericGenerator(name = "person_drinks_seq", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "person_drinks_id_seq"))
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
        EntityManager em = new PersonDrink().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countPersonDrinkses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PersonDrink o", Long.class).getSingleResult();
    }

	public static List<PersonDrink> findAllPersonDrinkses() {
        return entityManager().createQuery("SELECT o FROM PersonDrink o", PersonDrink.class).getResultList();
    }

	public static List<PersonDrink> findAllPersonDrinkses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM PersonDrink o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, PersonDrink.class).getResultList();
    }

	public static PersonDrink findPersonDrinks(Long id) {
        if (id == null) return null;
        return entityManager().find(PersonDrink.class, id);
    }

	public static List<PersonDrink> findPersonDrinksEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PersonDrink o", PersonDrink.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<PersonDrink> findPersonDrinksEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM PersonDrink o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, PersonDrink.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            PersonDrink attached = PersonDrink.findPersonDrinks(this.id);
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
    public PersonDrink merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PersonDrink merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
