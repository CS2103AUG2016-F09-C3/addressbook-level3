package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;

import java.util.*;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose names / tags contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Default Search\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t\t"
            + "Example: " + COMMAND_WORD + " alice bob charlie\n"
            + "Search by Tags\n\t"
            + "Parameters: -t KEYWORD [MORE_KEYWORDS]...\n\t\t"
            + "Example: " + COMMAND_WORD + "-t alice bob charlie";

    private final Set<String> keywords;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> personsFound = null;

        if (keywords.contains("-t")) {
            keywords.remove("-t");
            personsFound = getPersonsWithTagContainingAnyKeyword(keywords);
        }
        else{
            personsFound = getPersonsWithNameContainingAnyKeyword(keywords);
        }


        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieve all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();

        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<String> wordsInName = new HashSet<>(person.getName().getWordsInName());
            if (!Collections.disjoint(wordsInName, keywords)) {
                matchedPersons.add(person);
            }

        }
        return matchedPersons;
    }

    private List<ReadOnlyPerson> getPersonsWithTagContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();

        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<String> tagNames = new HashSet<>();
            for (Tag tag : person.getTags())
                tagNames.add(tag.getName());

            if (!Collections.disjoint(tagNames, keywords)) {
                matchedPersons.add(person);
            }

        }
        return matchedPersons;
    }
    
    @Override 
    public boolean isMutating(){ 
        return false; 
    }
}
