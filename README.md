# Eurosport Technical Test
## Description
This is the technical test for Eurosport <br />
The goal of the app is to retrieve the feed of articles using the RESTful API provided by Eurosport and display them in a single screen list <br />
Each item is clickable and opens the action for the item type : <br />
- Story: opens the description of the story with more details on the story like the content of the article <br />
- Video: opens the streaming of the provided video <br /><br />

It has been implemented according to the Clean Archi and with MVVM and MVI as for the design patterns <br />

## Why not Dagger?
In my experience, I've never implemented Dagger on a project from scratch <br />
I've always added dependencies from an already existing system <br />
So, for this project, I've decided to stick with what I knew best. Hence Koin

## Why MVI?
MVI is a strong design pattern that simplifies the code by a lot and improves the code readability for the UI part <br /><br />
In this test for example, in the same screen we have 3 different state possible : load the data, display the data (and handle errors for another time 😉) <br />
To have a more readable code base, I stick with MVI! <br />

## Why not 100% code coverage?
That is a great question indeed! Unfortunately for me, I couldn't take more time on this, otherwise I wouldn't have finished yet <br />
Please be kind with me haha <br />

## APK
You'll find the release v1.0 ![here](https://github.com/mikael-armonia/eurosport-technical-test/releases/tag/v1.0) with the APK included <br />
Happy testing!
<br /><br />

That's it for me! I hope you will have a good time reading all of this and I wish you a great day!<br /><br />
