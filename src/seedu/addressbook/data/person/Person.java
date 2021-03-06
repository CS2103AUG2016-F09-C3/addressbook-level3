package seedu.addressbook.data.person;

import seedu.addressbook.data.tag.UniqueTagList;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Person implements ReadOnlyPerson, Comparable<Person>{

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;

    private final UniqueTagList tags;
    /**
     * Assumption: Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, UniqueTagList tags) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Person(ReadOnlyPerson source) {
        this(source.getName(), source.getPhone(), source.getEmail(), source.getAddress(), source.getTags());
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Phone getPhone() {
        return phone;
    }

    @Override
    public Email getEmail() {
        return email;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this person's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyPerson // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyPerson) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return getAsTextShowAll();
    }
    
    @Override
    public int compareTo(Person person2) {
        return Comparators.NAME.compare(this, person2);
    }

	public static class Comparators {

		public static Comparator<Person> NAME = new Comparator<Person>() {
			@Override
			public int compare(Person person1, Person person2) {
				return person1.getName().fullName.compareTo(person2.getName().fullName);
			}
		};
		public static Comparator<Person> PHONE = new Comparator<Person>() {
			@Override
			public int compare(Person person1, Person person2) {
				return person1.getPhone().value.compareTo(person2.getPhone().value);
			}
		};
		public static Comparator<Person> EMAIL = new Comparator<Person>() {
			@Override
			public int compare(Person person1, Person person2) {
				return person1.getEmail().value.compareTo(person2.getEmail().value);
			}
		};
		public static Comparator<Person> ADDRESS = new Comparator<Person>() {
			@Override
			public int compare(Person person1, Person person2) {
				return person1.getAddress().value.compareTo(person2.getAddress().value);
			}
		};
	}

    public void setName(Name name) {
        this.name =name;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
