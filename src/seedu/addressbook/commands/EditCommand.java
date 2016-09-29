package seedu.addressbook.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.person.UniquePersonList.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.common.Messages;
import seedu.addressbook.commands.AddCommand;

public class EditCommand extends Command{

	public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Edits all the details of the person identified by the provided index. All details have to be entered again.\n"
            + "Contact details can be marked private by prepending 'p' to the prefix.\n\t"
            + "Parameters: INDEX NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n\t"
            + "Example: " + COMMAND_WORD
            + " 3 John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";

    public static final String MESSAGE_SUCCESS = "Edit successful: %1$s";

    private final Person toEdit;

    public EditCommand(int targetIndex, 
    		String name, 
    		String phone, boolean isPhonePrivate,
    		String email, boolean isEmailPrivate,
    		String address, boolean isAddressPrivate,
    		Set<String> tags) 
    				throws IllegalValueException {
    	
    	super(targetIndex);
    	
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
        	addressBook.removePerson(getTargetPerson());
            addressBook.addPerson(toEdit);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toEdit));

        } catch (UniquePersonList.DuplicatePersonException dpe) {
        	return new CommandResult(AddCommand.MESSAGE_DUPLICATE_PERSON);
        } catch (PersonNotFoundException pnfe){
        	return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (IndexOutOfBoundsException ie) {
        	return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

	
}
