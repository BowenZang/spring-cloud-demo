package cn.markzang.springcloud.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 * User实体类
 *
 * @author BowenZang
 * @since 2018年12月25日
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 7503399025564870303L;
	
	public User() {
	}
	
	public User(String name) {
		this(name, null);
	}
	
	public User(String name, Integer age) {
		this(name, age, null);
	}
	
	public User(String name, Integer age, Integer gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	
	private Long id;
	
	private String name;
	
	private Integer age;
	
	/**
	 * 1:男 2:女 3:未知
	 */
	private Integer gender;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Integer getGender() {
		return gender;
	}
	
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(age, user.age)
				&& Objects.equals(gender, user.gender);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name, age, gender);
	}
	
	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", gender=" + gender + '}';
	}
	
}
