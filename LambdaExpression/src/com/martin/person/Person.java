package com.martin.person;

public class Person {
	public enum Gender {
		MALE,
		FEMALE
	}
	
	private int age;
	private Gender sex;
	
	public Person(int age, Gender sex) {
		super();
		this.age = age;
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}
}
