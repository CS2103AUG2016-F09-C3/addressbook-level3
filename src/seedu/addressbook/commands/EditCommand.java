package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.ui.Gui;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Edits a exsting person's detail identified using index number in the list. \n\t"
            + "Contact details can be marked private by prepending 'p' to the prefix.\n\t"
            + "Parameters: INDEX n/NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n\t"
            + "Example: " + COMMAND_WORD
            + " 1 n/John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";

    public static final String MESSAGE_SUCCESS = "This person's detail has been edited: %1$s";

    private final Person toEdit;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand(int indexOfPerson, String name,
                      String phone, boolean isPhonePrivate,
                      String email, boolean isEmailPrivate,
                      String address, boolean isAddressPrivate,
                      Set<String> tags) throws IllegalValueException {
        super(indexOfPerson);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toEdit = new Person(
                new Name(name),
                new Phone(phone, isPhonePrivate),
                new Email(email, isEmailPrivate),
                new Address(address, isAddressPrivate),
                new UniqueTagList(tagSet)
        );
    }

    @Override
    public CommandResult execute() {
        try {
            editPersonInAddressbook(findPersonUsingIndex());
            return new CommandResult(String.format(MESSAGE_SUCCESS, toEdit));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX +
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
    }

    public Person findPersonUsingIndex() throws IndexOutOfBoundsException {
        return ((List<Person>) addressBook.getAllPersons().getList()).get(getTargetIndex() - Gui.DISPLAYED_INDEX_OFFSET);
    }

    private void editPersonInAddressbook(Person person) {
        person.setName(toEdit.getName());
        person.setPhone(toEdit.getPhone());
        person.setEmail(toEdit.getEmail());
        person.setAddress(toEdit.getAddress());
        person.setTags(toEdit.getTags());
    }
}
