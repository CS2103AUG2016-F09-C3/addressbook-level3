package seedu.addressbook.commands;

import java.util.List;

import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

public class ViewAllTags extends Command{

    public static final String COMMAND_WORD = "viewalltags";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Views all the tags. \n\t"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_VIEW_ALL_TAGS_SUCCESS = "All Tags has been displayed.";

    @Override
    public CommandResult execute() {
        List<Tag> allTags = addressBook.getAllTags().getList();
        return new CommandResult(getTagsShowAll(allTags) + MESSAGE_VIEW_ALL_TAGS_SUCCESS);
    }

    public String getTagsShowAll(List<Tag> internalList) {
        int index=1;
        final StringBuilder builder = new StringBuilder();
        for (Tag tag : internalList) {
            builder.append(index++ + " " + tag + "\n");
        }
        return builder.toString();
    }
}
