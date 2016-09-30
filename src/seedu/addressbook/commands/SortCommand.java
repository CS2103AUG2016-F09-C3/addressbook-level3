package seedu.addressbook.commands;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

public class SortCommand extends Command{

	
	public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Sort all persons in the address book by specified field.\n\t"
    		+ "Fields allowed are: name, phone, email, address.\n\t"
            + "Default field is name.\n\t"
            + "Example: " + COMMAND_WORD + " name";

    public static final String MESSAGE_SUCCESS = "List Sorted";
    
    private final String field;

    public SortCommand() throws IllegalValueException {
    	this("name");
    }
    
    public SortCommand(String field) throws IllegalValueException {
    	this.field = field;
    }

    @Override
    public CommandResult execute() {
    	
        addressBook.sort(field);
        
        return new CommandResult(MESSAGE_SUCCESS + " by " + field + "!");
    }
    
    @Override 
    public boolean isMutating(){ 
        return true; 
    }
}
