Video Demonstration: 
https://www.youtube.com/watch?v=P02JqNmdug8


Non-functional Requirement:
1. This web testing is based on the safari browser. To test the source code a safari is needed. 
The test is not restricted in safari, but I wrote the test code based on it.

2.If you’re using the Safari Webdriver, there are additional steps required to configure Safari to permit it to be used. 
Please see https://developer.apple.com/documentation/webkit/testing_with_webdriver_in_safari and follow the instructions 
in the section ‘Configure Safari to Enable WebDriver Support’. However, you can always see my video demonstration instead.

Tasks:
- The webpage loads with Home – University of Victoria as its title.
- The webpage contains a search button (the magnifying glass in the upper right)
- When the button is pressed, the search bar appears.
- When the letters csc are typed in that search bar, they appear correctly.
- When the search for csc is launched (either by clicking the search button at the end of
    the search bar, or by sending Keys.ENTER, a new webpage loads with Search –
    University of Victoria as its title.
- When you load uvic.ca, somewhere on that page, the phone number 1-250-721-7211
appears. Note test must confirm that the phone number is 1-250-721-7211.
