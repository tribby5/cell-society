#Refactoring Lab: mrt28 and ah365

#Code Smells Refactoring
1. Make a separate method that will generate a button when called and handed the proper parameters. One or two instances of duplicated code from this aspect had to do with the repeated code that went into creating and then modifying the buttons. For the most part, these actions are similar for generating a lot of buttons. Therefore we can put all of these calls in one method, and pass in the necessary aspects that an individual button needs. The tricky part about this will be setting the action of the button, because often times it will require a lot more.
2. Make an abstract method in the superclass of Locator and NoLocator called addChangers. The code is duplicated in both of these classes, and both serve the same general purpose although they will be carried out differently. Thus, by making it an abstract method higher up in the hierarchy, it allows the programmer to later delineate between the method of the two different classes. 

#Checklist Refactoring
1. Remove the magic values in the Game of Life simulation. Right now he has 2 and 3 as the minimum and maximum thresholds for when a cell would die, but this doesn't allow for possibly changing the threshold down the road if the actual shell changes shape or otherwise.
2. The methods get kinda lengthy here and there. 