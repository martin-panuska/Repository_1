package com.martin.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.martin.person.Person;
import com.martin.person.PersonTester;
import com.martin.person.Person.Gender;

public class Tester {

	public static void main(String[] args) {
		System.out.println(Optional.ofNullable(null).isPresent());
		Optional<String> o1 = Optional.ofNullable("zz");
		System.out.println(o1.orElseGet(Tester::getString));
		System.out.println(o1.filter((String s) -> s.equals("zz")).orElse("yy"));
	}
	
	public static String getString() {
		return "xx";
	}
	
	public static void testLambda() {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person(23, Gender.FEMALE));
		persons.add(new Person(15, Gender.MALE));
		persons.add(new Person(18, Gender.MALE));
		/** Old approach */
		printPersons(persons, new PersonTester() {
			@Override
			public boolean testPerson(Person p) {
				return p.getSex() == Gender.FEMALE;
			}
		});
		/** Lambda expression */
		printPersons(persons,
			(Person p) -> p.getSex() == Gender.FEMALE
		);
		/** Functional interface */
		printPersonsFunctional(persons,
			(Person p) -> p.getSex() == Gender.FEMALE
		);
		/** Functional interface with consumer */
		processPersonsFunctional(persons,
			(Person p) -> p.getSex() == Gender.FEMALE,
			(Person p) -> {
				System.out.println(p.getSex() + ", " + p.getAge());
				System.out.println("Done");
			}
		);
		/** Function pointers */
		persons.forEach((Person p) -> System.out.println(p));
		persons.forEach(System.out::println);
		/** Streams */
		System.out.println("xxx");
		persons.stream().filter((Person p) -> p.getSex() == Gender.FEMALE).forEach(System.out::println);
		
		System.out.println(
			persons.stream().map(Person::getAge).reduce(
				new Integer(0), 
				(Integer i, Integer p) -> i + p 
			)
		);
		
		Collector<Person, ?, Integer> c = Collectors.summingInt((Person p) -> p.getAge());
		persons.stream().collect(c);
		
		System.out.println("mmm");
		CollectHander mainCh = persons.parallelStream().collect(
			() -> new CollectHander(),
			CollectHander::accept,
			CollectHander::combine
		);
		System.out.println(mainCh.females);
		
		CollectHander mainCh2 = persons.parallelStream().collect(
			() -> new CollectHander(),
			(CollectHander ch, Person p) -> ch.accept(p),
			(CollectHander ch1, CollectHander ch2) -> {
				ch1.totalAge = ch2.totalAge = (ch1.totalAge + ch2.totalAge);
				ch1.females = ch2.females = (ch1.females + ch2.females);
				ch1.males = ch2.males = (ch1.males + ch2.males);
			}
		);
		System.out.println(mainCh2.females);

	}
	
	public static class CollectHander implements Consumer<Person> {
		private int totalAge = 0;
		private int females;
		private int males;

		public void combine(CollectHander ch2) {
			totalAge = ch2.totalAge;
			females = ch2.females;
			males = ch2.males;
		}
		@Override
		public void accept(Person p) {
			totalAge += p.getAge();
			females += p.getSex() == Gender.FEMALE? 1: 0;
			males += p.getSex() == Gender.MALE? 1: 0;
		}
		
		public int getTotalAge() {
			return totalAge;
		}
		public void setTotalAge(int totalAge) {
			this.totalAge = totalAge;
		}
		public int getFemales() {
			return females;
		}
		public void setFemales(int females) {
			this.females = females;
		}
		public int getMales() {
			return males;
		}
		public void setMales(int males) {
			this.males = males;
		}
	}
	
	public static void processPersonsFunctional(List<Person> persons, Predicate<Person> predicate, Consumer<Person> consumer) {
		for (Person p : persons) {
	        if (predicate.test(p)) {
	            consumer.accept(p);
	        }
	    }
	}
	
	public static void printPersonsFunctional(List<Person> persons, Predicate<Person> predicate) {
		for (Person p : persons) {
	        if (predicate.test(p)) {
	            System.out.println(p.getSex() + ", " + p.getAge());
	        }
	    }
	}
	
	public static void printPersons(List<Person> persons, PersonTester tester) {
		for (Person p : persons) {
	        if (tester.testPerson(p)) {
	            System.out.println(p.getSex() + ", " + p.getAge());
	        }
	    }
	}
}