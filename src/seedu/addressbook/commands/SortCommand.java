package seedu.addressbook.commands;

import java.util.List;

import seedu.addressbook.data.person.ReadOnlyPerson;

public class SortCommand extends Command{

	
	public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Sort all persons in the address book by name.\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "List Sorted!";

    @Override
    public CommandResult execute() {
    	
        addressBook.sort();
        
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
