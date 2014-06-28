package com.livebetter.domain;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(schema = "public",name = "persons")
@Configurable
public class Person {

	@Id
    @GeneratedValue(generator = "persons_seq")
	@GenericGenerator(name = "persons_seq", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "persons_id_seq"))
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
        EntityManager em = new Person().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countPersonses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Person o", Long.class).getSingleResult();
    }

	public static List<Person> findAllPersonses() {
        return entityManager().createQuery("SELECT o FROM Person o", Person.class).getResultList();
    }

	public static List<Person> findAllPersonses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Person o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Person.class).getResultList();
    }

	public static Person findPersons(Long id) {
        if (id == null) return null;
        return entityManager().find(Person.class, id);
    }

	public static List<Person> findPersonsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Person o", Person.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Person> findPersonsEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Person o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Person.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static BigDecimal getPersonCaloricIntakeForLast24Hours(Long personId) {
        String jpaQuery = String.format(
                "SELECT sum(personMeal.mealId.calories) from PersonMeal personMeal where personId.id = %d and (datetimeOfConsumtion >= (current_date() - 1))",
                personId);
        List results = entityManager().createQuery(jpaQuery).getResultList();
        if (results == null || results.size() == 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(((Number)results.get(0)).doubleValue());
    }

    public static BigDecimal getPersonBurnedCaloriesForLast24Hours(Long personId) {
        String jpaQuery = String.format(
                "SELECT sum(personActivity.activityId.caloriesPerHour * personActivity.quantity) from PersonActivity personActivity where personId.id = %d and (datetimeOfConsumtion >= (current_date () - 1))",
                personId);
        List results = entityManager().createQuery(jpaQuery).getResultList();
        if (results == null || results.size() == 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(((Number)results.get(0)).doubleValue());
    }

    public static BigDecimal getPersonHoursOfTrainingForLast24Hours(Long personId) {
        String jpaQuery = String.format(
                "SELECT sum(personActivity.quantity) from PersonActivity personActivity where personId.id = %d and (datetimeOfConsumtion >= (current_date () - 1))",
                personId);
        List results = entityManager().createQuery(jpaQuery).getResultList();
        if (results == null || results.size() == 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(((Number)results.get(0)).doubleValue());
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
            Person attached = Person.findPersons(this.id);
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
    public Person merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Person merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("personActivitieses", "personDrinkss", "personMealss", "usersToPersonss", "metabolismId").toString();
    }

	@OneToMany(mappedBy = "personId")
    private Set<PersonActivity> personActivitieses;

	@OneToMany(mappedBy = "personId")
    private Set<PersonDrink> personDrinkss;

	@OneToMany(mappedBy = "personId")
    private Set<PersonMeal> personMealss;

	@OneToMany(mappedBy = "personId")
    private Set<UserToPerson> usersToPersonss;

	@ManyToOne
    @JoinColumn(name = "metabolism_id", referencedColumnName = "id")
    @Deprecated
    private Metabolism metabolismId;

    @Column(name= "metabolism_type")
    @Enumerated(EnumType.STRING)
    private PersonMetabolism metabolismType
            ;
    @Column(name= "gender")
    @Enumerated(EnumType.STRING)
    private PersonGender gender;

	@Column(name = "firstname", length = 45)
    @NotNull
    private String firstname;

	@Column(name = "middlename", length = 45)
    private String middlename;

	@Column(name = "lastname", length = 45)
    private String lastname;

	@Column(name = "date_of_birth")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar dateOfBirth;

	@Column(name = "height")
    private Long height;

	@Column(name = "weight")
    private Long weight;

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

    @Column(name = "goal_weight")
    private BigDecimal goalWeight;

    @Column(name = "goal_deadline")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar goalDeadline;


    public Set<PersonActivity> getPersonActivitieses() {
        return personActivitieses;
    }

	public void setPersonActivitieses(Set<PersonActivity> personActivitieses) {
        this.personActivitieses = personActivitieses;
    }

	public Set<PersonDrink> getPersonDrinkss() {
        return personDrinkss;
    }

	public void setPersonDrinkss(Set<PersonDrink> personDrinkss) {
        this.personDrinkss = personDrinkss;
    }

	public Set<PersonMeal> getPersonMealss() {
        return personMealss;
    }

	public void setPersonMealss(Set<PersonMeal> personMealss) {
        this.personMealss = personMealss;
    }

	public Set<UserToPerson> getUsersToPersonss() {
        return usersToPersonss;
    }

	public void setUsersToPersonss(Set<UserToPerson> usersToPersonss) {
        this.usersToPersonss = usersToPersonss;
    }

	public Metabolism getMetabolismId() {
        return metabolismId;
    }

	public void setMetabolismId(Metabolism metabolismId) {
        this.metabolismId = metabolismId;
    }

	public String getFirstname() {
        return firstname;
    }

	public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

	public String getMiddlename() {
        return middlename;
    }

	public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

	public String getLastname() {
        return lastname;
    }

	public void setLastname(String lastname) {
        this.lastname = lastname;
    }

	public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

	public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

	public Long getHeight() {
        return height;
    }

	public void setHeight(Long height) {
        this.height = height;
    }

	public Long getWeight() {
        return weight;
    }

	public void setWeight(Long weight) {
        this.weight = weight;
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

    public PersonMetabolism getMetabolismType() {
        return metabolismType;
    }

    public void setMetabolismType(PersonMetabolism metabolismType) {
        this.metabolismType = metabolismType;
    }

    public BigDecimal getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(BigDecimal goalWeight) {
        this.goalWeight = goalWeight;
    }

    public Calendar getGoalDeadline() {
        return goalDeadline;
    }

    public void setGoalDeadline(Calendar goalDeadline) {
        this.goalDeadline = goalDeadline;
    }

    public PersonGender getGender() {
        return gender;
    }

    public void setGender(PersonGender gender) {
        this.gender = gender;
    }
}
