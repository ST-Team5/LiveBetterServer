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
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(schema = "public",name = "meals")
@Configurable
public class Meal {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("personMealss").toString();
    }

	@Id
    @GeneratedValue(generator = "meals_seq")
	@GenericGenerator(name = "meals_seq", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "meals_id_seq"))
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
        EntityManager em = new Meal().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countMealses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Meal o", Long.class).getSingleResult();
    }

	public static List<Meal> findAllMealses() {
        return entityManager().createQuery("SELECT o FROM Meal o", Meal.class).getResultList();
    }

	public static List<Meal> findFrequentMealsForUser(Long userId) {
		String query = String.format("select m from Meal m join m.personMealss as pm where pm.personId = %d group by m.id order by count(pm)", userId);
		return entityManager().createQuery(query, Meal.class).getResultList();
	}

	public static List<Meal> findRecentMealssForUser(Long userId) {
		String query = String.format("select m from Meal m join m.personMealss as pm where pm.personId = %d group by m.id order by max(pm.datetimeOfConsumtion)", userId);
		return entityManager().createQuery(query, Meal.class).getResultList();
	}

	public static List<Meal> findAllMealses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Meal o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Meal.class).getResultList();
    }

	public static Meal findMeals(Long id) {
        if (id == null) return null;
        return entityManager().find(Meal.class, id);
    }

	public static List<Meal> findMealsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Meal o", Meal.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Meal> findMealsEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Meal o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Meal.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
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
            Meal attached = Meal.findMeals(this.id);
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
    public Meal merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Meal merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	@OneToMany(mappedBy = "mealId")
    private Set<PersonMeal> personMealss;

	@Column(name = "name", length = 45)
    @NotNull
    private String name;

	@Column(name = "type", length = 45)
    @NotNull
    private String type;

	@Column(name = "carbohydrates")
    private Long carbohydrates;

	@Column(name = "proteins")
    private Long proteins;

	@Column(name = "fat")
    private Long fat;

	@Column(name = "calories")
    private Long calories;

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

	public Set<PersonMeal> getPersonMealss() {
        return personMealss;
    }

	public void setPersonMealss(Set<PersonMeal> personMealss) {
        this.personMealss = personMealss;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String getType() {
        return type;
    }

	public void setType(String type) {
        this.type = type;
    }

	public Long getCarbohydrates() {
        return carbohydrates;
    }

	public void setCarbohydrates(Long carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

	public Long getProteins() {
        return proteins;
    }

	public void setProteins(Long proteins) {
        this.proteins = proteins;
    }

	public Long getFat() {
        return fat;
    }

	public void setFat(Long fat) {
        this.fat = fat;
    }

	public Long getCalories() {
        return calories;
    }

	public void setCalories(Long calories) {
        this.calories = calories;
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
}
